package gov.taxation.dao.impl;

import gov.taxation.dao.DaoFactory;
import gov.taxation.dao.ReportDao;
import gov.taxation.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public ReportDao createReportDao() {
        return new JDBCReportDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
