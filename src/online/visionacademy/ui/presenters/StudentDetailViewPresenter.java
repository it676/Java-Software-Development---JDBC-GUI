package online.visionacademy.ui.presenters;

import java.util.List;
import online.visionacademy.entities.Course;
import online.visionacademy.entities.Student;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.exceptions.StudentNotFoundException;
import online.visionacademy.services.CourseService;
import online.visionacademy.services.CourseServiceImpl;
import online.visionacademy.services.StudentService;
import online.visionacademy.services.StudentServiceImpl;
import online.visionacademy.ui.views.StudentDetailView;

public class StudentDetailViewPresenter implements StudentDetailViewListener {

    private StudentDetailView view;
    private StudentService studentService;
    private CourseService coruseService;

    public StudentDetailViewPresenter(StudentDetailView view) {
        this.view = view;
        this.view.addListener(this);
        studentService = new StudentServiceImpl();
        coruseService = new CourseServiceImpl();
    }

    @Override
    public void searchStudent() throws ServiceException, StudentNotFoundException {

        Long stdId = Long.parseLong(view.getStudentId());
        Student std = studentService.getById(stdId);

        view.setIsStudentFound(true);
        String[] stdInfoItems = {std.getId() + "", std.getNationalityId() + "",
            std.getFirstName() + " " + std.getLastName(), std.getDob() + ""};

        //coruses
        List<Course> courseList = coruseService.getAllByStudent(stdId);
        String[] registeredCoruseItems = new String[courseList.size()];

        if (courseList.isEmpty()) {
            registeredCoruseItems = new String[]{"No courses registered for " + std.getFirstName()};
        } else {
            for (int i = 0; i < courseList.size(); i++) {
                Course course = courseList.get(i);
                registeredCoruseItems[i] = course.getName();
            }
        }
        view.setStudentInfoItems(stdInfoItems);
        view.setRegisteredCoursesItems(registeredCoruseItems);
    }
}
