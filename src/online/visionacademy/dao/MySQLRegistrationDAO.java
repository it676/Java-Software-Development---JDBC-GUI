package online.visionacademy.dao;

import online.visionacademy.entities.Registration;
import online.visionacademy.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLRegistrationDAO extends RegistrationDAO {

    private static final String TABLE_NAME = "course_student";
    private static final String[] cols = {"course_id", "student_id"};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getCols() {
        return cols;
    }

    @Override
    protected void setStatementParas(PreparedStatement ps, Registration reg) throws DAOException {
        boolean isInsert = reg.getId() == null;
        try {
            ps.setLong(1, reg.getCourseId());
            ps.setLong(2, reg.getStudentId());

            if (!isInsert) {
                ps.setLong(3, reg.getId());
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    protected Registration mapObject(ResultSet rs) throws DAOException {
        Registration reg = new Registration();
        try {
            reg.setId(rs.getLong("id"));
            reg.setCourseId(rs.getLong("course_id"));
            reg.setStudentId(rs.getLong("student_id"));

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

        return reg;
    }

    @Override
    protected List<Registration> mapAllObjects(ResultSet rs) throws DAOException {
        List<Registration> regList = new ArrayList<>();
        try {
            while (rs.next()) {
                Registration reg = mapObject(rs);
                regList.add(reg);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return regList;
    }

    @Override
    public List<Registration> findByStudentId(Long studentId) throws DAOException {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE student_id = ?";
        return findBy(studentId, query);
    }

    @Override
    public List<Registration> findByCourseId(Long courseId) throws DAOException {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE course_id = ?";
        return findBy(courseId, query);

    }

    private List<Registration> findBy(Long id, String query) throws DAOException {
        List<Registration> regList = new ArrayList<>();
        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                regList = mapAllObjects(rs);
            }
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

        return regList;
    }

}
