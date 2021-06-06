package gov.taxation.command;

import gov.taxation.dao.DBException;
import gov.taxation.dao.entity.Report;
import gov.taxation.dao.entity.ReportStatusEnum;
import gov.taxation.service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Create a new report.
 * Get report date, rate as input, make validation.
 * <p>
 * Set errorMsg if validation failed or cannot create.
 */
public class ReportCreateCommand implements Command {

    private final ReportService reportService;

    public ReportCreateCommand() {
        this.reportService = new ReportService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String reportDate = request.getParameter("reportDate");
        String rate = request.getParameter("rate");

        if (Validator.hasEmptyFields(request, reportDate, rate)) {
            return "/WEB-INF/report-form.jsp";
        }
        try {
            reportService.create(Report.builder()
                    .reportDate(LocalDate.parse(reportDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .rate(new BigDecimal(rate))
                    .user(CommandUtility.getUserFromSession(request))
                    .status(ReportStatusEnum.PENDING)
                    .build());
            return "redirect:/home";
        } catch (DBException e) {
            logger.warn(e.getMessage());
            request.setAttribute("errorMsg", e.getMessage());
            return "/WEB-INF/report-form.jsp";
        }
    }
}


