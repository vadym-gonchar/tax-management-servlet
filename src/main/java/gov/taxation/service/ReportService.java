package gov.taxation.service;

import gov.taxation.dao.DBException;
import gov.taxation.dao.DaoFactory;
import gov.taxation.dao.ReportDao;
import gov.taxation.dao.entity.Report;
import gov.taxation.dto.Page;
import gov.taxation.utils.Prop;

public class ReportService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    /**
     * Get Page of reports
     *
     * @param pageNo current page
     * @param sort   sort field
     * @param direct sort direction (ASC or DESC)
     * @return Page
     * @throws DBException if cannot get page
     */
    public Page getAllPageable(int pageNo, String sort,
                               String direct) throws DBException {
        try (ReportDao dao = daoFactory.createReportDao()) {
            return dao.findAllPageable(pageNo, sort, direct);
        }
    }

    /**
     * Get report for report update page
     *
     * @param id report id
     * @return report
     * @throws DBException if cannot find
     */
    public Report findById(Long id) throws DBException {
        try (ReportDao dao = daoFactory.createReportDao()) {
            return dao.findById(id.intValue()).orElseThrow(
                    () -> new DBException(Prop.getDBProperty("select.reports.dbe") + id));
        }
    }

    /**
     * Create new report
     *
     * @param report report
     * @return Report
     * @throws DBException if cannot create
     */
    public Report create(Report report) throws DBException {
        try (ReportDao dao = daoFactory.createReportDao()) {
            return dao.create(report).orElseThrow(
                    () -> new DBException(
                            Prop.getDBProperty("create.reports.dbe"))
            );
        }
    }

    /**
     * Update report
     *
     * @param report Report
     */
    public void update(Report report) {
        try (ReportDao dao = daoFactory.createReportDao()) {
            dao.update(report);
        }
    }
}

