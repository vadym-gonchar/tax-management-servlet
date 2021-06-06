package gov.taxation.service;

import gov.taxation.dao.DBException;
import gov.taxation.dao.DaoFactory;
import gov.taxation.dao.UserDao;
import gov.taxation.dao.entity.User;
import gov.taxation.utils.Prop;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * For registration, hash password
     *
     * @param user User
     * @return User
     * @throws DBException if DB throw exception
     */
    public User create(User user) throws DBException {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));

        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user).orElseThrow(
                    () -> new DBException(
                            Prop.getDBProperty("create.user.dbe") + user.getUserName())
            );
        }
    }

    /**
     * For login page
     *
     * @param username String username
     * @return User
     * @throws DBException if DB throws exception
     */
    public User findByUsername(String username) throws DBException {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username).orElseThrow(() -> new DBException(
                    Prop.getDBProperty("select.login.byLogin.dbe") + username)
            );
        }
    }
}

