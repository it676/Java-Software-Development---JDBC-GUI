package online.visionacademy.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import online.visionacademy.dao.AbstractDAO;
import online.visionacademy.entities.Identifiable;
import online.visionacademy.exceptions.DAOException;
import online.visionacademy.exceptions.PersistenceException;

public abstract class AbstractRepository<T extends Identifiable, ID> implements GenericRepository<T, ID> {

    private AbstractDAO<T, ID> dao;

    public AbstractRepository() {
        dao = getAbstractDAO();
    }

    public abstract AbstractDAO getAbstractDAO();

    @Override
    public Integer count() throws PersistenceException {
        try {
            return dao.count();
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean contains(ID id) throws PersistenceException {
        try {
            Optional<T> optional = dao.readById(id);
            if (optional.isPresent()) {
                return true;
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public T add(T entity) throws PersistenceException {
        try {
            return dao.insert(entity);
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<T> addAll(List<T> entities) throws PersistenceException {
        for (T entity : entities) {
            add(entity);
        }
        return entities;
    }

    @Override
    public Optional<T> findById(ID id) throws PersistenceException {
        try {
            return dao.readById(id);
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<T> findByColumn(String column, String value) throws PersistenceException {
        try {
            return dao.readByColmun(column, value);
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<T> findAll() throws PersistenceException {
        try {
            return dao.readAll();
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<T> findAllById(List<ID> ids) throws PersistenceException {
        List<T> list = new ArrayList<>();
        try {
            if (ids.size() > 0) {
                list = dao.readAllById(ids);
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }

        return list;
    }

    @Override
    public T update(T entity) throws PersistenceException {
        try {
            return dao.update(entity);
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void removeById(ID id) throws PersistenceException {
        try {
            boolean isExists = contains(id);
            if (isExists) {
                dao.delete(id);
            } else {
                throw new PersistenceException("Could not delete, no such object with id : " + id);
            }
        } catch (DAOException ex) {
            throw new PersistenceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void remove(T entity) throws PersistenceException {
        ID id = (ID) entity.getId();
        removeById(id);
    }

    @Override
    public void removeAllById(List<ID> ids) throws PersistenceException {
        for (ID id : ids) {
            removeById(id);
        }
    }
}
