package gov.taxation.command;

import gov.taxation.dao.DBException;
import gov.taxation.dao.entity.ReportStatusEnum;
import gov.taxation.dto.Page;
import gov.taxation.service.ReportService;

import javax.servlet.http.HttpServletRequest;

/**
 * Get current page, sort field, sort direction, makes validation.
 * <p>
 * Set errorMsg if validation failed or cannot find list (input invalid).
 */
public class MainCommand implements Command {
    private final ReportService reportService;

    public MainCommand() {
        this.reportService = new ReportService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page = request.getParameter("page");
        String sort = request.getParameter("sort");
        String direct = request.getParameter("direct");
        String status = request.getParameter("status");

        int pageNo = (Validator.hasEmptyFields(request, page)) ? 1 : Integer.parseInt(page);
        sort = (Validator.hasEmptyFields(request, sort)) ? "id" : sort;
        direct = (Validator.hasEmptyFields(request, direct)) ? "ASC" : direct;

        Page pageable;
        try {
            pageable = reportService.getAllPageable(pageNo, sort, direct);
        } catch (DBException e) {
            logger.info(e.getMessage());
            request.setAttribute("errorMsg", e.getMessage());
            return "/WEB-INF/home.jsp";
        }
        request.setAttribute("reportItems", pageable.getReportDTO());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("totalPages", pageable.getTotalPages());
        request.setAttribute("sort", sort);
        request.setAttribute("direct", direct);
        request.setAttribute("directTable", (direct.equals("ASC")) ? "DESC" : "ASC");
        request.setAttribute("statuses", ReportStatusEnum.values());
        request.setAttribute("status", status);

        return "/WEB-INF/home.jsp";
    }
}

