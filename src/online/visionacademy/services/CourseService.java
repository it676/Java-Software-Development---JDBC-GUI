package online.visionacademy.services;

import java.util.List;
import online.visionacademy.dtos.CourseDTO;
import online.visionacademy.entities.Course;
import online.visionacademy.exceptions.CourseNotFoundException;
import online.visionacademy.exceptions.ServiceException;

public interface CourseService {

    public abstract Course add(Course course) throws ServiceException;

    //read
    public abstract Course getById(Long id) throws ServiceException, CourseNotFoundException;

    public abstract CourseDTO getCourseWithStudents(Long id) throws ServiceException, CourseNotFoundException;

    public abstract Course getByCode(String code) throws ServiceException, CourseNotFoundException;

    public abstract List<Course> getAll() throws ServiceException;

    public abstract List<Course> getAllByStudent(Long id) throws ServiceException;

    public abstract boolean contains(Long id) throws ServiceException;

    //counts
    public abstract Integer count() throws ServiceException;

    public abstract Integer studentCount(Long id) throws ServiceException;

    //update
    public abstract Course update(Course course) throws ServiceException, CourseNotFoundException;

    //remove
    public abstract void removeById(Long id) throws ServiceException, CourseNotFoundException;

    public abstract void remove(Course course) throws ServiceException, CourseNotFoundException;

    public abstract void removeAllById(List<Long> ids) throws ServiceException, CourseNotFoundException;

}
