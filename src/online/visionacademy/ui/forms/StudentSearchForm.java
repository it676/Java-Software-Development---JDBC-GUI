package online.visionacademy.ui.forms;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import online.visionacademy.ui.common.ButtonFactory;
import online.visionacademy.ui.common.PaddingManager;
import online.visionacademy.ui.omponents.RoundedCornerBorder;
import online.visionacademy.ui.omponents.RoundedTextField;
import online.visionacademy.ui.omponents.Text;
import online.visionacademy.ui.omponents.TextWithIcon;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class StudentSearchForm extends JPanel {

    private RoundedTextField studentIdField;
    private JButton searchBtn;

    public StudentSearchForm() {
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

        initSearchBtn();

        add(formTitle);
        add(Box.createRigidArea(new Dimension(0, 16)));
        add(fields);
        add(Box.createRigidArea(new Dimension(0, 8)));
        add(searchBtn);
    }

    private void initSearchBtn() {
        searchBtn = ButtonFactory.createButton("Search", Color.WHITE,
                Color.decode(AppColor.SEC_BACKGROUND),
                MaterialDesign.MDI_MAGNIFY);

        searchBtn.setAlignmentX(CENTER_ALIGNMENT);
        searchBtn.setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    private JPanel createForm() {
        JPanel fields = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        fields.setBackground(Color.WHITE);
        fields.add(new TextWithIcon("Student Id", Color.decode(AppColor.SUBTITLE), 14, Font.PLAIN, MaterialDesign.MDI_ACCOUNT_CARD_DETAILS));
        fields.add(studentIdField);
        return fields;
    }

    private void initFields() {
        studentIdField = new RoundedTextField();
    }

    private Text createFormTitle() {
        Text formTitle = new Text("Search Student", Color.decode(AppColor.TITLE));
        formTitle.setAlignmentX(CENTER_ALIGNMENT);
        return formTitle;
    }

    public RoundedTextField getStudentIdField() {
        return studentIdField;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

}
