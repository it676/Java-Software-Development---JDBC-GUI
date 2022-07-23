package online.visionacademy.ui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import online.visionacademy.ui.common.UI;
import online.visionacademy.ui.omponents.NavigatablePanel;
import online.visionacademy.ui.routes.NavigationItem;
import online.visionacademy.ui.util.AppColor;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class Dashboard extends JPanel {

    public Dashboard() {
        init();
    }

    private void init() {

        setName("Dashboard");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        NavigatablePanel studentsCard = new NavigatablePanel("Students", "Add, remove and edit students", Color.WHITE, MaterialDesign.MDI_ACCOUNT_MULTIPLE, NavigationItem.STUDENT_DASHBOARD);
        NavigatablePanel coursesCard = new NavigatablePanel("Courses", "Add, remove and edit courses", Color.WHITE, MaterialDesign.MDI_LAPTOP_CHROMEBOOK, NavigationItem.COURSE_DASHBOARD);
        NavigatablePanel registrationsCard = new NavigatablePanel("Registrations", "Register coruses for students", Color.WHITE, MaterialDesign.MDI_FILE_DOCUMENT_BOX, NavigationItem.COMMING_SOON);
        NavigatablePanel reportsCard = new NavigatablePanel("Reports", "Export and print reports", Color.WHITE, MaterialDesign.MDI_FILE_PDF, NavigationItem.COMMING_SOON);
        NavigatablePanel settingsCard = new NavigatablePanel("Settings", "Customize and control settings", Color.WHITE, MaterialDesign.MDI_SETTINGS, NavigationItem.COMMING_SOON);

        add(studentsCard);
        add(coursesCard);
        add(registrationsCard);
        add(reportsCard);
        add(settingsCard);
    }
}
