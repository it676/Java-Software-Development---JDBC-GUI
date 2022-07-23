package online.visionacademy.dao;

import online.visionacademy.entities.Registration;
import online.visionacademy.exceptions.DAOException;
import java.util.List;

public abstract class  RegistrationDAO extends MySQLDAO<Registration> {

    public abstract List<Registration> findByStudentId(Long studentId) throws DAOException;
    public abstract List<Registration> findByCourseId(Long courseId) throws DAOException;

}
