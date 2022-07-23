package online.visionacademy.ui.presenters;

import java.time.LocalDate;
import online.visionacademy.entities.Student;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.services.StudentService;
import online.visionacademy.services.StudentServiceImpl;
import online.visionacademy.ui.views.AddStudentView;

public class AddStudentViewPresenter implements AddStudentViewListener {

    private AddStudentView view;
    private StudentService studentService;

    public AddStudentViewPresenter(AddStudentView view) {
        this.view = view;
        this.view.addListener(this);
        studentService = new StudentServiceImpl();
    }

    @Override
    public void addStudent() throws ServiceException {
        if (view.isFormValid()) {
            Long natId = Long.parseLong(view.getNationalityId());
            String firstName = view.getFirstName();
            String lastName = view.getLastName();
            String dob = view.getDOB();
            LocalDate birthDate = LocalDate.parse(dob);
            studentService.add(new Student(natId, firstName, lastName, birthDate));
        }
    }
}
