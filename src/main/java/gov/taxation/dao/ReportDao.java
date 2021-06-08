package gov.taxation.dao;

import gov.taxation.dao.entity.Report;
import gov.taxation.dto.Page;
import gov.taxation.dao.entity.User;

public interface ReportDao extends GenericDao<Report> {
    Page findAllPageable(int pageNo, String sort, String direct, User user) throws DBException;
}
