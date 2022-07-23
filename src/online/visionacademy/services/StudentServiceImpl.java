package online.visionacademy.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import online.visionacademy.dtos.StudentDTO;
import online.visionacademy.entities.Course;
import online.visionacademy.entities.Student;
import online.visionacademy.exceptions.CourseNotFoundException;
import online.visionacademy.exceptions.PersistenceException;
import online.visionacademy.exceptions.ServiceException;
import online.visionacademy.exceptions.StudentNotFoundException;
import online.visionacademy.exceptions.ValidationException;
import online.visionacademy.mappers.StudentMapper;
import online.visionacademy.mappers.StudentMapperImpl;
import online.visionacademy.repositories.CourseRepository;
import online.visionacademy.repositories.CourseRepositoryImpl;
import online.visionacademy.repositories.StudentRepository;
import online.visionacademy.repositories.StudentRepositoryImpl;
import online.visionacademy.validators.LengthValidator;
import online.visionacademy.validators.NullValidator;
import online.visionacademy.validators.Validator;

public class StudentServiceImpl implements StudentService {

    private StudentRepository stdRepository;
    private CourseRepository courseRepository;

    public StudentServiceImpl() {
        stdRepository = new StudentRepositoryImpl();
        courseRepository = new CourseRepositoryImpl();
    }

    @Override
    public Student add(Student std) throws ServiceException {
        checkNull("Student", std);
        validateStudent(std);

        String nationalityId = String.valueOf(std.getNationalityId());
        try {
            List<Student> studentList = stdRepository.findByColumn("nat_id", nationalityId);
            if (studentList.size() > 0) {
                throw new ValidationException("Student with nationality id " + nationalityId + " already exists");
            }

            return stdRepository.add(std);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not save student.");
        }
    }

    @Override
    public List<Student> addAll(List<Student> studentList) throws ServiceException {
        for (Student student : studentList) {
            add(student);
        }
        return studentList;
    }

    @Override
    public Student getById(Long id) throws ServiceException, StudentNotFoundException {
        try {
            checkNull("Id", id);
            Optional<Student> optionalStudent = stdRepository.findById(id);
            if (!optionalStudent.isPresent()) {
                throw new StudentNotFoundException("Stuent with id " + id + " not found.");
            }
            Student std = optionalStudent.get();
            return std;
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not featch student.");
        }
    }

    @Override
    public List<Student> getAll() throws ServiceException {

        try {
            return stdRepository.findAll();
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not featch students.");
        }
    }

    @Override
    public List<Student> getAllById(List<Long> ids) throws ServiceException {
        try {
            return stdRepository.findAllById(ids);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not featch students.");
        }
    }

    @Override
    public List<Student> getByFirstName(String firstName) throws ServiceException {
        try {
            return stdRepository.findByColumn("first_name", firstName);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not featch students.");
        }
    }

    @Override
    public List<Student> getAllByCourse(Long id) throws ServiceException {
        try {
            checkNull("Id", id);
            return stdRepository.findByCourseId(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not featch students.");
        }
    }

    @Override
    public List<Student> getStudentsWithCourses(Integer count) throws ServiceException {
        checkNull("Courses Count", count);
        List<Student> studentList = new ArrayList<>();
        for (Student std : getAll()) {
            if (courseCount(std.getId()).equals(count)) {
                studentList.add(std);
            }
        }
        return studentList;
    }

    @Override
    public List<Student> getStudentsWithNoCourses() throws ServiceException {
        return getStudentsWithCourses(0);
    }

    @Override
    public boolean contains(Long id) throws ServiceException {
        try {
            checkNull("Id", id);
            return stdRepository.contains(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not featch students.");
        }
    }

    @Override
    public Integer count() throws ServiceException {
        try {
            return stdRepository.count();
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not count students.");
        }
    }

    @Override
    public Integer courseCount(Long id) throws ServiceException {

        try {
            checkNull("Id", id);
            return stdRepository.courseCount(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not count students.");
        }
    }

    @Override
    public Student update(Student student) throws ServiceException, StudentNotFoundException {

        checkNull("Student", student);
        Long id = student.getId();
        if (!contains(id)) {
            throw new StudentNotFoundException("Can't update student, student with id " + id + " not found");
        }
        try {
            return stdRepository.update(student);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not update student.");
        }
    }

    @Override
    public void removeById(Long id) throws ServiceException, StudentNotFoundException {
        try {
            checkNull("Id", id);
            if (!contains(id)) {
                throw new StudentNotFoundException("Can't remove student, student with id " + id + " not found");
            }

            stdRepository.removeById(id);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not remove student.");
        }
    }

    @Override
    public void removeById(Student student) throws ServiceException, StudentNotFoundException {
        checkNull("Student", student);
        removeById(student.getId());
    }

    @Override
    public void removeAllById(List<Long> ids) throws ServiceException, StudentNotFoundException {
        for (Long id : ids) {
            removeById(id);
        }
    }

    @Override
    public void registerForCourse(Long courseId, Long studentId) throws ServiceException, StudentNotFoundException, CourseNotFoundException {
        checkNull("Course Id", courseId);
        checkNull("Student Id", studentId);

        try {
            if (!contains(studentId)) {
                throw new StudentNotFoundException("Student with id " + studentId + " not found");
            }

            //Logic and Rule
            if (courseCount(studentId) >= 5) {
                throw new ValidationException("Student can't register more than 5 courses.");
            }

            if (!courseRepository.contains(courseId)) {
                throw new CourseNotFoundException("Course with id " + courseId + " not found");
            }

            //Logic and Rule
            if (courseRepository.studentCount(courseId) >= 40) {
                throw new ValidationException("Can't register more than 40 students.");
            }
            //Logic 
            if (courseRepository.isRegistred(courseId, studentId)) {
                throw new ValidationException("Student is already registered for the course with id " + courseId);

            }
            courseRepository.register(courseId, studentId);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not register student.");
        }

    }

    @Override
    public void cancelCourse(Long courseId, Long studentId) throws ServiceException {

        try {
            checkNull("Course Id", courseId);
            checkNull("Student Id", studentId);
            courseRepository.deRegister(courseId, studentId);
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not register student.");
        }
    }

    @Override
    public void cancelAll(Long studentId) throws ServiceException {
        try {
            checkNull("Student Id", studentId);
            for (Course course : courseRepository.findByStudentId(studentId)) {
                cancelCourse(course.getId(), studentId);
            }
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not register student.");
        }
    }

    private void checkNull(String field, Object obj) {
        Validator validator = new NullValidator(field, obj);
        validator.validate();
        validator = null;
    }

    public void validateStudent(Student std) {

        String natId = std.getNationalityId().toString();
        String firstName = std.getFirstName();
        String lastName = std.getLastName();

        //validate nationality id
        LengthValidator validator = new LengthValidator("Nationality Id", natId, 10, 11);
        validator.validate();

        //validate first name
        validator.setValues("First Name", firstName, 2, 10);
        validator.validate();

        //validate last name
        validator.setValues("Last Name", lastName, 2, 10);
        validator.validate();

        //validate the date
        validator = null;

    }

    @Override
    public StudentDTO getStudentCourses(Long id) throws ServiceException, StudentNotFoundException {

        try {
            StudentMapper mapper = new StudentMapperImpl();
            Student student = getById(id);
            StudentDTO stdDTO = mapper.toDTO(student);

            List<String> courseList = courseRepository.findByStudentId(id).stream()
                    .map(course -> course.getName())
                    .collect(Collectors.toList());
           
            stdDTO.setCourseList(courseList);
            return stdDTO;
        } catch (PersistenceException ex) {
            throw new ServiceException("Could not fetch courses of the student");
        }
    }
}
