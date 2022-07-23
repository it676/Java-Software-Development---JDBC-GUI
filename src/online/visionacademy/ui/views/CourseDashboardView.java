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

public class CourseDashboardView extends JPanel {

    public CourseDashboardView() {
        init();
    }

    private void init() {
        setName("Manage Courses");
        setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        setBackground(Color.decode(AppColor.BACKGROUND));

        NavigatablePanel addCourse = new NavigatablePanel("New Course", "Add new course", Color.WHITE, MaterialDesign.MDI_PLUS_BOX, NavigationItem.ADD_COURSE);
        NavigatablePanel diaplayCourse = new NavigatablePanel("Search Coruse", "Search a course", Color.WHITE, MaterialDesign.MDI_MAGNIFY, NavigationItem.DISPLAY_COURSE);
        NavigatablePanel allCourses = new NavigatablePanel("Display All", "Display all courses", Color.WHITE, MaterialDesign.MDI_FILE_DOCUMENT_BOX, NavigationItem.LIST_COURSES);

        add(addCourse);
        add(diaplayCourse);
        add(allCourses);

    }
}
