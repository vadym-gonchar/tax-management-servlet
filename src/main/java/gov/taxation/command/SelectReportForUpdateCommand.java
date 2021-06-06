package gov.taxation.command;

import gov.taxation.dao.DBException;
import gov.taxation.dao.entity.Report;
import gov.taxation.service.ReportService;

import javax.servlet.http.HttpServletRequest;

/**
 * Show report update page
 * Get report id as input, make validation
 */
public class SelectReportForUpdateCommand implements Command {
    private final ReportService reportService;

    public SelectReportForUpdateCommand() {
        this.reportService = new ReportService();
    }

    @Override
    public String execute(HttpServletRequest request) {

        String id = request.getParameter("id");
        if (Validator.hasEmptyFields(request, id)) {

            return "redirect:/home";
        }
        logger.info("Select report, id: " + id);
        try {
            Report report = reportService.findById(Long.parseLong(id));
            request.setAttribute("reportUpdate", report);

            return "/WEB-INF/report-form.jsp";

        } catch (DBException e) {
            logger.warn(e.getMessage());
            request.setAttribute("errorMsg", e.getMessage());

            return "/WEB-INF/home.jsp";
        }
    }
}

