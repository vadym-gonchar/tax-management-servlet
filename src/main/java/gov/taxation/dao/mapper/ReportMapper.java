package gov.taxation.dao.mapper;

import gov.taxation.dao.entity.Report;
import gov.taxation.dao.entity.ReportStatusEnum;
import gov.taxation.dao.entity.RoleType;
import gov.taxation.dao.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportMapper implements ObjectMappers<Report> {

    @Override
    public Report extractFromResultSet(ResultSet rs) throws SQLException {
        return Report.builder()
                .id(rs.getLong("id"))
                .reportDate(LocalDate.parse(rs.getString("report_date")))
                .comment(rs.getString("comment"))
                .rate(rs.getBigDecimal("rate"))
                .createdAt(LocalDateTime.parse(rs.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(ReportStatusEnum.valueOf(rs.getString("status").toUpperCase()))
                .user(User.builder()
                        .id(rs.getLong("users_id"))
                        .userName(rs.getString("username"))
                        .password(rs.getString("password"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .role(RoleType.valueOf(rs.getString("role")))
                        .build())
                .build();
    }
}

