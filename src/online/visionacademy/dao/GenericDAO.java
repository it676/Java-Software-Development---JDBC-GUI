package online.visionacademy.dao;

//crud operations
import online.visionacademy.entities.Identifiable;
import online.visionacademy.exceptions.DAOException;
import java.util.List;
import java.util.Optional;

public interface GenericDAO<T extends Identifiable, ID> {

    //create
    public abstract T insert(T entity) throws DAOException;

    //read
    public abstract Optional<T> readById(ID id) throws DAOException;

    public abstract List<T> readByColmun(String column, String value) throws DAOException;

    public abstract List<T> readAll() throws DAOException;

    public abstract List<T> readAllById(List<ID> ids) throws DAOException;

    //update
    public abstract T update(T entity) throws DAOException;

    //delete
    public abstract void delete(ID id) throws DAOException;
}
