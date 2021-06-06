package gov.taxation.dao;

import gov.taxation.dao.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsername(String username) throws DBException;
}
