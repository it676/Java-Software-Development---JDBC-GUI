package online.visionacademy.repositories;

import java.util.List;
import online.visionacademy.entities.Course;
import online.visionacademy.exceptions.PersistenceException;

public abstract class CourseRepository extends AbstractRepository<Course, Long> {

    public abstract List<Course> findByStudentId(Long studentId) throws PersistenceException;

    public abstract void removeAllRegistrations(Long courseId) throws PersistenceException;

    //students
    public abstract Integer studentCount(Long courseId) throws PersistenceException;

    public abstract boolean isRegistred(Long courseId, Long studentId) throws PersistenceException;

    public abstract void register(Long courseId, Long studentId) throws PersistenceException;

    public abstract void deRegister(Long courseId, Long studentId) throws PersistenceException;

}
