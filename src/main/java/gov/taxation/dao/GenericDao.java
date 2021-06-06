package gov.taxation.dao;

import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    Optional<T> create(T entity) throws DBException;

    Optional<T> findById(int id) throws DBException;

    void update(T entity);

    void close();
}
