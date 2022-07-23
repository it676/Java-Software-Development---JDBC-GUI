package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.ui.common.Alert;
import online.visionacademy.ui.common.ButtonFactory;
import online.visionacademy.ui.forms.ContactForm;
import online.visionacademy.ui.forms.PersonalInfoForm;
import online.visionacademy.ui.presenters.AddStudentViewListener;
import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.routes.Router;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

public class AddStudentView extends JPanel {

    private List<AddStudentViewListener> listeners = new ArrayList<>();

    private PersonalInfoForm personalInfoForm = new PersonalInfoForm();
    private ContactForm contactForm = new ContactForm();

    public AddStudentView() {
        init();
    }

    private void init() {

        setName("New Student");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        personalInfoForm.setMaximumSize(personalInfoForm.getPreferredSize());
        contactForm.setMaximumSize(contactForm.getPreferredSize());

        personalInfoForm.setAlignmentY(TOP_ALIGNMENT);
        contactForm.setAlignmentY(TOP_ALIGNMENT);

        JButton addStudentBtn = ButtonFactory.createButton("Add Student", Color.WHITE,
                Color.decode(AppColor.SEC_BACKGROUND),
                MaterialDesign.MDI_PLUS_CIRCLE);

        addStudentBtn.setAlignmentX(CENTER_ALIGNMENT);

        addStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyListenersOnAddStudentClicked();
            }
        });

        add(personalInfoForm);
        add(contactForm);
        add(addStudentBtn);
    }

    public boolean isFieldValid(String value, int min, int max) {

        if (value == null) {
            return false;
        }

        value = value.trim();

        if (value.isEmpty()) {
            return false;
        }

        int length = value.length();

        if (length < min || length > max) {
            return false;
        }

        return true;
    }

    private boolean validateForm() {

        String natId = getNationalityId();
        String firstName = getFirstName();
        String lastName = getLastName();
        String dob = getDOB();

        if (!isFieldValid(natId, 10, 11)) {
            showWarningMessage("Nationality Id");
            return false;
        }

        if (!isFieldValid(firstName, 2, 10)) {
            showWarningMessage("First Name");
            return false;
        }

        if (!isFieldValid(lastName, 2, 10)) {
            showWarningMessage("Last Name");
            return false;
        }
        if (!isFieldValid(dob, 10, 10)) {
            showWarningMessage("Birth Date");
            return false;
        }
        return true;
    }

    public boolean isFormValid() {
        return validateForm();
    }

    public void showWarningMessage(String fieldName) {
        Alert.warning(this.getRootPane(), "Invalid value for " + fieldName, "Invalid Input");
    }

    //reset the form
    public void resetForm() {
        personalInfoForm.getNationalityIdField().setText("");
        personalInfoForm.getFirstNameField().setText("");
        personalInfoForm.getLastNameField().setText("");
        personalInfoForm.getDobField().setText("");
    }
    //get field values

    public String getNationalityId() {
        return personalInfoForm.getNationalityIdField().getText();
    }

    public String getFirstName() {
        return personalInfoForm.getFirstNameField().getText();
    }

    public String getLastName() {
        return personalInfoForm.getLastNameField().getText();
    }

    public String getDOB() {
        return personalInfoForm.getDobField().getText();

    }

    public void addListener(AddStudentViewListener listener) {
        this.listeners.add(listener);
    }

    public void notifyListenersOnAddStudentClicked() {
        for (AddStudentViewListener listener : listeners) {
            try {

                if (!isFormValid()) {
                    return;
                }

                listener.addStudent();

                Alert.info(this.getRootPane(),
                        "Student has been saved successfully",
                        "Add Student",
                        FontIcon.of(MaterialDesign.MDI_CHECK_CIRCLE, 40, Color.decode(AppColor.TITLE)));

                resetForm();
                Router.getInstance().navigate(NavigationItem.LIST_STUDENTS);

            } catch (ServiceException ex) {
                Alert.error(this.getRootPane(), ex.getMessage(), "Add Student");
            } catch (DateTimeParseException ex) {
                Alert.error(this.getRootPane(), "Invalid Birth date\nDate format should be yyyy-mm-dd", "Add Student");
            } catch (NumberFormatException ex) {
                Alert.error(this.getRootPane(), "Nationality id must be a number", "Add Student");
            } catch (Exception ex) {
                Alert.error(this.getRootPane(), ex.getMessage(), "Add Student");
            }

        }
    }
}
