package gov.taxation.dao.impl;

import gov.taxation.dao.DBException;
import gov.taxation.dao.ReportDao;
import gov.taxation.dao.entity.Report;
import gov.taxation.dao.mapper.ReportMapper;
import gov.taxation.dto.Page;
import gov.taxation.dto.converter.ReportDTOConverter;
import gov.taxation.utils.Prop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Math.min;

public class JDBCReportDao implements ReportDao {
    Logger log = LogManager.getLogger(JDBCReportDao.class);
    private final Connection connection;

    public JDBCReportDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Report> create(Report entity) throws DBException {

        final String query = "INSERT INTO reports (report_date, rate, users_id, status) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(entity.getReportDate()));
            pstmt.setBigDecimal(2, entity.getRate());
            pstmt.setLong(3, entity.getUser().getId());
            pstmt.setString(4, entity.getStatus().getName());

            pstmt.executeUpdate();

            return Optional.of(entity);

        } catch (SQLException ex) {
            String errorMsg = Prop.getDBProperty("create.reports.dbe");
            log.error(errorMsg);
            throw new DBException(errorMsg);
        }
    }

    /**
     * Update report
     * @param entity Report
     */
    @Override
    public void update(Report entity) {
        String query = "update reports set report_date=?, rate=?, comment=?, status=? where id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(entity.getReportDate()));
            pstmt.setBigDecimal(2, entity.getRate());
            pstmt.setString(3, entity.getComment());
            pstmt.setString(4, entity.getStatus().getName());
            pstmt.setLong(5, entity.getId());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            String errorMsg = Prop.getDBProperty("update.reports.dbe") + entity.getId();
            log.error(errorMsg);
        }
    }

    /**
     * Find reports to update
     *
     * @param id report id
     * @return Report
     * @throws DBException if cannot find
     */
    @Override
    public Optional<Report> findById(int id) throws DBException {
        Optional<Report> result = Optional.empty();

        final String query = "select r.*, u.* from reports r" +
                "  left join users u on r.users_id = u.id" +
                "  where r.id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            ReportMapper reportMapper = new ReportMapper();
            if (rs.next()) {
                result = Optional.of(reportMapper.extractFromResultSet(rs));
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            String errorMsg = Prop.getDBProperty("select.reports.dbe");
            log.error(errorMsg);
            throw new DBException(errorMsg);
        }
    }

    /**
     * Find all reports for home page
     *
     * @param pageNo current page
     * @param sort   sort field
     * @param direct direction ASC or DESC
     * @return Page class
     * @throws DBException if cannot find
     */
    public Page findAllPageable(int pageNo, String sort, String direct) throws DBException {

        int rowsOnPage = Integer.parseInt(Prop.getProperty("pageable.page"));
        int beginNo = (pageNo - 1) * rowsOnPage;
        int endNo = beginNo + rowsOnPage;

        Long totalPages;
        List<Report> reportList = new ArrayList<>();

        String query = "" +
                "select r.*, u.* from reports r" +
                "  left join users u on r.users_id = u.id" +
                " order by " + ("id".equals(sort) ? "r.id" : sort) + " " + direct;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            connection.setAutoCommit(false);
            ResultSet rs = pstmt.executeQuery();
            connection.commit();

            Long totalRows = resultSetMapper(rs, reportList);
            reportList = reportList.subList(beginNo, (int) min(totalRows, endNo));
            totalPages = getTotal(totalRows, rowsOnPage);
            rs.close();

            return Page.builder()
                    .reports(ReportDTOConverter.convertList(reportList))
                    .totalPages(totalPages)
                    .build();

        } catch (SQLException e) {
            String errorMsg = Prop.getDBProperty("select.all.reports.dbe");
            log.error(errorMsg);
            try {
                log.warn(Prop.getDBProperty("transaction.rollback"));
                connection.rollback();
            } catch (SQLException ex) {
                log.error(Prop.getDBProperty("transaction.rollback.fail"));
            }
            throw new DBException(errorMsg);
        }
    }

    /**
     * Internal mapper for all reports
     *
     * @param rs         reports result set
     * @param reportList report list
     * @throws SQLException if cannot get from rs
     */
    private Long resultSetMapper(ResultSet rs, List<Report> reportList) throws SQLException {
        ReportMapper reportMapper = new ReportMapper();
        while (rs.next()) {
            reportList.add(reportMapper.extractFromResultSet(rs));
        }
        return (long) reportList.size();
    }

    /**
     * Internal util for counting of total pages
     *
     * @param totalRows  amount of reports
     * @param rowsOnPage amount rows by page
     */
    private Long getTotal(Long totalRows, int rowsOnPage) {
        if (totalRows != null) {
            totalRows = (totalRows % rowsOnPage == 0)
                    ? totalRows / rowsOnPage
                    : totalRows / rowsOnPage + 1;
        }
        return totalRows;
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

