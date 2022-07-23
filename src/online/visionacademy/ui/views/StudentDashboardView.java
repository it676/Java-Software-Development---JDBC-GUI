package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.omponents.NavigatablePanel;
import online.visionacademy.ui.omponents.Text;
import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class StudentDashboardView extends JPanel {

    public StudentDashboardView() {
        init();
    }

    private void init() {
        setName("Manage Students");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));
        
        NavigatablePanel addStudent = new NavigatablePanel("New Student", "Add new student", Color.WHITE, MaterialDesign.MDI_PLUS_BOX, NavigationItem.ADD_STUDENT);
        NavigatablePanel displayStudent = new NavigatablePanel("Display Student", "Search a student", Color.WHITE, MaterialDesign.MDI_ACCOUNT_CARD_DETAILS, NavigationItem.DISPLAY_STUDENT);
        NavigatablePanel allStudents = new NavigatablePanel("Show All", "Display all students", Color.WHITE, MaterialDesign.MDI_FILE_DOCUMENT_BOX, NavigationItem.LIST_STUDENTS);

        add(addStudent);
        add(displayStudent);
        add(allStudents);

    }
}
