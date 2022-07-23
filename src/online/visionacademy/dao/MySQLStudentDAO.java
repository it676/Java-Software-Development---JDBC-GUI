package online.visionacademy.dao;

import online.visionacademy.datasource.ConnectionFactory;
import online.visionacademy.datasource.DataSourceType;
import online.visionacademy.entities.Student;
import online.visionacademy.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import online.visionacademy.support.QueryBuilder;

public class MySQLStudentDAO  extends StudentDAO {

    private static final String TABLE_NAME = "students";
    private static final String[] cols = {"nat_id", "first_name", "last_name", "dob"};

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getCols() {
        return cols;
    }

    @Override
    protected void setStatementParas(PreparedStatement ps, Student entity) throws DAOException {
        boolean isInsert = entity.getId() == null;
        try {
            ps.setLong(1, entity.getNationalityId());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, entity.getDob().toString());

            if (!isInsert) {
                ps.setLong(5, entity.getId());
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

    }

    @Override
    protected Student mapObject(ResultSet rs) throws DAOException {
        Student student = new Student();
        try {
            student.setId(rs.getLong("id"));
            student.setNationalityId(rs.getLong("nat_id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setDob(LocalDate.parse(rs.getString("dob")));
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

        return student;
    }

    @Override
    protected List<Student> mapAllObjects(ResultSet rs) throws DAOException {
        List<Student> studentList = new ArrayList<>();
        try {
            while (rs.next()) {
                Student student = mapObject(rs);
                studentList.add(student);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return studentList;
    }


}
