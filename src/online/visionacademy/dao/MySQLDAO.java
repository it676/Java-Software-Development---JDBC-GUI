package online.visionacademy.dao;

import online.visionacademy.datasource.ConnectionFactory;
import online.visionacademy.datasource.DataSourceType;
import online.visionacademy.entities.Identifiable;
import online.visionacademy.entities.Student;
import online.visionacademy.exceptions.DAOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import online.visionacademy.support.QueryBuilder;

public abstract class MySQLDAO<T extends Identifiable> extends AbstractDAO<T, Long> {

    public abstract String getTableName();

    public abstract String[] getCols();

    @Override
    protected void setGeneratedKey(ResultSet rs, T entity) throws DAOException {
        try {
            if (rs != null && rs.next()) {
                Long id = rs.getLong(1);
                entity.setId(id);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    protected ConnectionFactory getConnectionFactory() {
        return ConnectionFactory.getConnectionFactory(DataSourceType.MYSQL);
    }

    @Override
    protected void setStatementWhereId(PreparedStatement ps, Long id) throws DAOException {
        try {
            ps.setLong(1, id);
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    protected void setStatementWhereColumn(PreparedStatement ps, String columnValue) throws DAOException {
        try {
            ps.setString(1, columnValue);
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    protected void setStatementParas(PreparedStatement ps, List<Long> ids) throws DAOException {
        try {
            int index = 1;
            for (Long id : ids) {
                ps.setLong(index++, id);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    protected String getInsertQuery() {
        final String TABLE_NAME = getTableName();
        final String[] cols = getCols();
        return QueryBuilder.insertQuery(TABLE_NAME, cols);
    }

    @Override
    protected String getSelectByIdQuery() {
        final String TABLE_NAME = getTableName();
        return QueryBuilder.selectOne(TABLE_NAME);
    }

    @Override
    protected String getSelectByColumnQuery(String columnName) {
        final String TABLE_NAME = getTableName();
        return QueryBuilder.selectOneWhereColumn(TABLE_NAME, columnName);
    }

    @Override
    protected String getSelectAllQuery() {
        final String TABLE_NAME = getTableName();
        return QueryBuilder.selectAll(TABLE_NAME);
    }

    @Override
    protected String getSelectAllByIdQuery(List<Long> ids) {
        final String TABLE_NAME = getTableName();
        return QueryBuilder.selectAllById(TABLE_NAME, ids.toArray(new Long[0]));
    }

    @Override
    protected String getUpdateQuery() {
        final String TABLE_NAME = getTableName();
        final String[] cols = getCols();

        return QueryBuilder.updateWhereId(TABLE_NAME, cols);
    }

    @Override
    protected String getDeleteQuery() {
        final String TABLE_NAME = getTableName();
        return QueryBuilder.deleteWhereId(TABLE_NAME);
    }

    @Override
    protected String getCountQuery() {
        final String TABLE_NAME = getTableName();
        return QueryBuilder.count(TABLE_NAME);
    }
}
