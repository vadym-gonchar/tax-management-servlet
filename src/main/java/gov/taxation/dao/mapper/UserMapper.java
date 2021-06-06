package gov.taxation.dao.mapper;

import gov.taxation.dao.entity.RoleType;
import gov.taxation.dao.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMappers<User> {

    @Override
    public User extractFromResultSet(ResultSet result) throws SQLException {
        return User.builder()
                .id(result.getLong("id"))
                .userName(result.getString("username"))
                .password(result.getString("password"))
                .firstName(result.getString("first_name"))
                .lastName(result.getString("last_name"))
                .email(result.getString("email"))
                .role(RoleType.valueOf(result.getString("role")))
                .build();
    }
}
