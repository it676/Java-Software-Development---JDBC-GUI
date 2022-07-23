package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.ui.common.Alert;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.omponents.ListCard;
import online.visionacademy.ui.forms.StudentSearchForm;
import online.visionacademy.ui.presenters.StudentDetailViewListener;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class StudentDetailView extends JPanel {

    private List<StudentDetailViewListener> listeners = new ArrayList<>();

    private final StudentSearchForm studentSearchForm = new StudentSearchForm();

    private String[] studentInfoItems = {""};
    private String[] registeredCoursesItems = {""};

    private ListCard studentInfoCard = new ListCard("Student Information",
            MaterialDesign.MDI_ACCOUNT,
            this.studentInfoItems,
            MaterialDesign.MDI_ACCOUNT_CARD_DETAILS);

    private ListCard registeredCoursesCard = new ListCard("Registered Courses",
            MaterialDesign.MDI_LAPTOP_CHROMEBOOK,
            this.registeredCoursesItems,
            MaterialDesign.MDI_BOOKMARK);
    ;

    private JLabel lbl = new JLabel();

    private boolean isStudentFound;

    public StudentDetailView() {
        init();
    }

    private void init() {
        setName("Student Details");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 8));
        setBackground(Color.decode(AppColor.BACKGROUND));
        PaddingManager.setPadding(this, 0, 0, 40, 0);

        studentSearchForm.setMaximumSize(studentSearchForm.getPreferredSize());
        studentInfoCard.setVisible(false);

        registeredCoursesCard.setVisible(false);

        JButton searchBtn = studentSearchForm.getSearchBtn();
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyListenersOnSearchStudentClicked();
            }
        });

        add(studentSearchForm);
        add(studentInfoCard);
        add(registeredCoursesCard);
        add(lbl);

    }

    //get data from field
    public String getStudentId() {
        String id = studentSearchForm.getStudentIdField().getText();
        return id;
    }

    public boolean isStudentFound() {
        return isStudentFound;
    }

    public void setIsStudentFound(boolean isStudentFound) {
        this.isStudentFound = isStudentFound;
    }

    public void setStudentInfoItems(String[] studentInfoItems) {
        this.studentInfoItems = studentInfoItems;
    }

    public void setRegisteredCoursesItems(String[] registeredCoursesItems) {
        this.registeredCoursesItems = registeredCoursesItems;
    }

    public void addListener(StudentDetailViewListener listener) {
        this.listeners.add(listener);
    }

    public void notifyListenersOnSearchStudentClicked() {
        for (StudentDetailViewListener listener : listeners) {

            try {

                listener.searchStudent();

                if (!isStudentFound()) {
                    Alert.warning(this.getRootPane(), "Student with this id not found", "Search Student");
                    studentInfoCard.setVisible(false);
                    registeredCoursesCard.setVisible(false);
                    return;
                }

                updatePanel();

            } catch (ServiceException ex) {
                System.out.println(ex);
                Alert.error(this.getRootPane(), ex.getMessage(), "Search Student");
            } catch (NumberFormatException ex) {
                Alert.error(this.getRootPane(), "student id must be a number", "Search Student");
            } catch (Exception ex) {
                Alert.error(this.getRootPane(), ex.getMessage(), "Search Student");
            }
        }
    }

    public void updatePanel() {
        lbl.setText(Arrays.toString(studentInfoItems));
        lbl.setVisible(false);
        this.removeAll();
        studentInfoCard = new ListCard("Student Information",
                MaterialDesign.MDI_ACCOUNT,
                this.studentInfoItems,
                MaterialDesign.MDI_ACCOUNT_CARD_DETAILS);

        studentInfoCard.addItems();

        registeredCoursesCard = new ListCard("Registered Courses",
                MaterialDesign.MDI_LAPTOP_CHROMEBOOK,
                this.registeredCoursesItems,
                MaterialDesign.MDI_BOOKMARK);
        registeredCoursesCard.addItems();

        this.repaint();
        this.invalidate();
        add(studentSearchForm);
        add(studentInfoCard);
        add(registeredCoursesCard);
        add(lbl);
    }
}
