package online.visionacademy.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.dao.CourseDAO;
import online.visionacademy.dao.MySQLCourseDAO;
import online.visionacademy.dao.MySQLRegistrationDAO;
import online.visionacademy.dao.RegistrationDAO;
import online.visionacademy.entities.Course;
import online.visionacademy.entities.Registration;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.exceptions.PersistenceException;

public class CourseRepositoryImpl extends CourseRepository {

    private CourseDAO courseDAO;
    private RegistrationDAO regDAO;

    public CourseRepositoryImpl() {
        courseDAO = (CourseDAO) getAbstractDAO();
        regDAO = new MySQLRegistrationDAO();
    }

    @Override
    public AbstractDAO getAbstractDAO() {
        return new MySQLCourseDAO();
    }

    @Override
    public Course add(Course course) throws PersistenceException {
        super.add(course);
        List<Long> ids = course.getStudentIds();
        for (Long studentId : ids) {
            if (!isRegistred(course.getId(), studentId)) {
                register(course.getId(), studentId);
            }
        }
        return course;
    }

    @Override
    public Optional<Course> findById(Long courseId) throws PersistenceException {
        Optional<Course> optionalCourse = super.findById(courseId);
        try {
            if (optionalCourse.isPresent()) {
                Course course = optionalCourse.get();
                List<Registration> regList = regDAO.findByCourseId(course.getId());
                List<Long> ids = regList.stream().map(Registration::getStudentId).collect(Collectors.toList());
                course.setStudentIds(ids);
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
        return optionalCourse;
    }

    @Override
    public List<Course> findAll() throws PersistenceException {
        List<Course> courseList = super.findAll();
        try {
            for (Course course : courseList) {
                List<Registration> regList = regDAO.findByCourseId(course.getId());
                List<Long> ids = regList.stream().map(Registration::getStudentId).collect(Collectors.toList());
                course.setStudentIds(ids);
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }

        return courseList;
    }

    @Override
    public Course update(Course course) throws PersistenceException {
        super.update(course);
        removeAllRegistrations(course.getId());

        List<Long> ids = course.getStudentIds();
        for (Long studentId : ids) {
            register(course.getId(), studentId);
        }
        return course;
    }

    @Override
    public void removeById(Long courseId) throws PersistenceException {
        removeAllRegistrations(courseId);
        super.removeById(courseId);
    }

    @Override
    public List<Course> findByStudentId(Long studentId) throws PersistenceException {
        List<Course> courseList = new ArrayList<>();
        try {
            List<Registration> regList = regDAO.findByStudentId(studentId);
            for (Registration reg : regList) {
                Optional<Course> optionalCourse = courseDAO.readById(reg.getCourseId());//findById
                if (optionalCourse.isPresent()) {
                    courseList.add(optionalCourse.get());
                }
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }

        return courseList;
    }

    @Override
    public void removeAllRegistrations(Long courseId) throws PersistenceException {
        try {
            for (Registration reg : regDAO.findByCourseId(courseId)) {
                regDAO.delete(reg.getId());
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Integer studentCount(Long courseId) throws PersistenceException {
        int count = 0;
        try {
            count = regDAO.findByCourseId(courseId).size();
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
        return count;
    }

    @Override
    public boolean isRegistred(Long courseId, Long studentId) throws PersistenceException {
        try {
            for (Registration reg : regDAO.findByCourseId(courseId)) {
                if (reg.getStudentId().equals(studentId)) {
                    return true;
                }
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public void register(Long courseId, Long studentId) throws PersistenceException {
        try {
            if (!isRegistred(courseId, studentId)) {
                regDAO.insert(new Registration(studentId, courseId));
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deRegister(Long courseId, Long studentId) throws PersistenceException {
        try {
            for (Registration reg : regDAO.findByCourseId(courseId)) {
                if (reg.getStudentId().equals(studentId)) {
                    regDAO.delete(reg.getId());
                    break;
                }
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

}
