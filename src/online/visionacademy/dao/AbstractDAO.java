package online.visionacademy.dao;

import online.visionacademy.datasource.ConnectionFactory;
import online.visionacademy.entities.Identifiable;
import online.visionacademy.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Identifiable, ID> implements GenericDAO<T, ID> {

    private static final int UPDATE_EXECUTED_SUCCESSFULLY = 1;

    protected abstract ConnectionFactory getConnectionFactory();

    protected abstract String getInsertQuery();

    protected abstract String getSelectByIdQuery();

    protected abstract String getSelectByColumnQuery(String columnName);

    protected abstract String getSelectAllQuery();

    protected abstract String getSelectAllByIdQuery(List<ID> ids);

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getCountQuery();

    //set the id for the where caluse in preparedStatement 
    protected abstract void setStatementWhereId(PreparedStatement ps, ID id) throws DAOException;

    protected abstract void setStatementWhereColumn(PreparedStatement ps, String value) throws DAOException;

    //set generated primary key
    protected abstract void setGeneratedKey(ResultSet rs, T entity) throws DAOException;

    //set the paramaters for the PrepaedStatement using the passed obj
    protected abstract void setStatementParas(PreparedStatement ps, T entity) throws DAOException;

    protected abstract void setStatementParas(PreparedStatement ps, List<ID> ids) throws DAOException;

    //map/read/convert the ResultSet into an object of type T
    protected abstract T mapObject(ResultSet rs) throws DAOException;

    protected abstract List<T> mapAllObjects(ResultSet rs) throws DAOException;

    @Override
    public T insert(T entity) throws DAOException {
        String insertQuery = getInsertQuery();

        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {

            setStatementParas(ps, entity);

            if (ps.executeUpdate() < UPDATE_EXECUTED_SUCCESSFULLY) {
                throw new DAOException("Could NOT save the object.");
            }

            setGeneratedKey(ps.getGeneratedKeys(), entity);

        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return entity;
    }

    @Override
    public Optional<T> readById(ID id) throws DAOException {
        T entity;
        String selectByIdQuery = getSelectByIdQuery();
        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(selectByIdQuery)) {

            setStatementWhereId(ps, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entity = mapObject(rs);
            } else {
                return Optional.empty();
            }

        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return Optional.of(entity);
    }

    @Override
    public List<T> readAll() throws DAOException {

        List<T> list = new ArrayList<>();
        String selectAllQuery = getSelectAllQuery();

        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(selectAllQuery)) {

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                list = mapAllObjects(rs);
            }

        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return list;
    }
    
    @Override
    public List<T> readByColmun(String column, String value) throws DAOException {

        List<T> list = new ArrayList<>();
        String selectByColumnQuery =   getSelectByColumnQuery(column);

        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(selectByColumnQuery)) {

            this.setStatementWhereColumn(ps, value);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                list = mapAllObjects(rs);
            }

        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public List<T> readAllById(List<ID> ids) throws DAOException {

        List<T> list = new ArrayList<>();
        String selectAllQuery = getSelectAllByIdQuery(ids);

        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(selectAllQuery)) {

            setStatementParas(ps, ids);

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                list = mapAllObjects(rs);
            }

        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public T update(T entity) throws DAOException {
        String updateQuery = getUpdateQuery();

        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(updateQuery)) {
            setStatementParas(ps, entity);
            if (ps.executeUpdate() < UPDATE_EXECUTED_SUCCESSFULLY) {
                throw new DAOException("Could not update the entity");
            }
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

        return entity;
    }

    @Override
    public void delete(ID id) throws DAOException {
        String deleteQuery = getDeleteQuery();

        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(deleteQuery)) {

            setStatementWhereId(ps, id);

            if (ps.executeUpdate() < UPDATE_EXECUTED_SUCCESSFULLY) {
                throw new DAOException("Could not delete the entity");
            }
        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    public Integer count() throws DAOException {
        String countQuery = getCountQuery();
        try (Connection connection = getConnectionFactory().createConnection();
                PreparedStatement ps = connection.prepareStatement(countQuery)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new DAOException("Could not count the entities.");
            }

        } catch (Exception ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
