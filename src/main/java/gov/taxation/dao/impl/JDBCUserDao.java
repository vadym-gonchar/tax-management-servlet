package gov.taxation.dao.impl;

import gov.taxation.dao.DBException;
import gov.taxation.dao.UserDao;
import gov.taxation.dao.entity.RoleType;
import gov.taxation.dao.entity.User;
import gov.taxation.dao.mapper.UserMapper;
import gov.taxation.utils.Prop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class JDBCUserDao implements UserDao {

    private static final Logger log = LogManager.getLogger(JDBCUserDao.class);
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> create(User entity) throws DBException {
        ResultSet rs;

        final String query = "INSERT INTO users (username, password, first_name, last_name, email, role) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, entity.getUserName());
            pstmt.setString(2, entity.getPassword());
            pstmt.setString(3, entity.getFirstName());
            pstmt.setString(4, entity.getLastName());
            pstmt.setString(5, entity.getEmail());
            pstmt.setString(6, RoleType.ROLE_USER.name());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    entity.setId(rs.getLong(1));
                }
                rs.close();
            }
            return Optional.of(entity);
        } catch (SQLException ex) {
            String errorMsg = Prop.getDBProperty("create.user.dbe") + entity.getUserName();
            log.error(errorMsg);
            throw new DBException(errorMsg);
        }
    }

    /**
     * For login page
     *
     * @param username username
     * @return login if found
     * @throws DBException if cannot find
     */
    public Optional<User> findByUsername(String username) throws DBException {
        Optional<User> result = Optional.empty();

        try (PreparedStatement pstmt =
                     connection.prepareStatement("SELECT * FROM users WHERE username=?")) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = Optional.of(new UserMapper().extractFromResultSet(rs));
            }
            rs.close();
            return result;
        } catch (SQLException ex) {
            String errorMsg = Prop.getDBProperty("select.login.byLogin.dbe") + username;
            log.error(errorMsg);
            throw new DBException(errorMsg);
        }
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

