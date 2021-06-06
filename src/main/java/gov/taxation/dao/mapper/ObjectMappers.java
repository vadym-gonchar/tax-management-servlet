package gov.taxation.dao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMappers<T> {
    T extractFromResultSet(ResultSet rs) throws SQLException;
}