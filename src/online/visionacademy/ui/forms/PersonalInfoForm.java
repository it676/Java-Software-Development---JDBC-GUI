package online.visionacademy.ui.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.common.PanelFactory;
import online.visionacademy.ui.omponents.RoundedCornerBorder;
import online.visionacademy.ui.omponents.RoundedTextField;
import online.visionacademy.ui.omponents.Text;
import online.visionacademy.ui.omponents.TextWithIcon;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class PersonalInfoForm extends JPanel {

    private RoundedTextField nationalityIdField;
    private RoundedTextField firstNameField;
    private RoundedTextField lastNameField;
    private RoundedTextField dobField;

    public PersonalInfoForm() {
        init();
    }

    private void init() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

//padding with radius border
        PaddingManager.setPaddingWithRadiusBorder(this, 20);

        Text formTitle = createFormTitle();
        initFields();
        JPanel fields = createForm();
        add(formTitle);
        add(fields);
    }

    private Text createFormTitle() throws NumberFormatException {
        Text formTitle = new Text("Personal Info", Color.decode(AppColor.TITLE));
        formTitle.setAlignmentX(CENTER_ALIGNMENT);
        return formTitle;
    }

    private JPanel createForm() throws NumberFormatException {
        JPanel fields = PanelFactory.createPanel(Color.WHITE);
        fields.setBorder(new EmptyBorder(20, 0, 0, 0));
        fields.setLayout(new GridLayout(4, 2, 8, 8));
        //nat id
        fields.add(new TextWithIcon("Nationality Id", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_ACCOUNT_CARD_DETAILS));
        fields.add(nationalityIdField);
        //first name
        fields.add(new TextWithIcon("First Name", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_ACCOUNT));
        fields.add(firstNameField);
        //last name
        fields.add(new TextWithIcon("Last Name", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_ACCOUNT));
        fields.add(lastNameField);
        //dob
        fields.add(new TextWithIcon("Birth Date", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_CALENDAR));
        fields.add(dobField);
        return fields;
    }

    private void initFields() {
        nationalityIdField = new RoundedTextField();
        firstNameField = new RoundedTextField();
        lastNameField = new RoundedTextField();
        dobField = new RoundedTextField();
    }

    //getters
    public RoundedTextField getNationalityIdField() {
        return nationalityIdField;
    }

    public RoundedTextField getFirstNameField() {
        return firstNameField;
    }

    public RoundedTextField getLastNameField() {
        return lastNameField;
    }

    public RoundedTextField getDobField() {
        return dobField;
    }

}
