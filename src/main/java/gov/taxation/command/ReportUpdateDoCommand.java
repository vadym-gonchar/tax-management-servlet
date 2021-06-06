package gov.taxation.command;

import gov.taxation.dao.entity.Report;
import gov.taxation.dao.entity.ReportStatusEnum;
import gov.taxation.service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Update report.
 * Get report id, reportDate, rate, comment, status as input, make validation.
 * <p>
 * Set errorMsg if validation failed.
 */
public class ReportUpdateDoCommand implements Command {

    private final ReportService reportService;

    public ReportUpdateDoCommand() {
        this.reportService = new ReportService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter("id");
        String reportDate = request.getParameter("reportDate");
        String rate = request.getParameter("rate");
        String comment = request.getParameter("comment");
        String status = request.getParameter("status");

        if (Validator.hasEmptyFields(request, id)) {
            return "redirect:/home";
        } else if (Validator.hasEmptyFields(request, reportDate, rate, status)) {
            return "/WEB-INF/report-form.jsp";
        }
        reportService.update(Report.builder()
                .id(Long.parseLong(id))
                .reportDate(LocalDate.parse(reportDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .rate(new BigDecimal(rate))
                .comment(comment)
                .status(ReportStatusEnum.valueOf(status.toUpperCase()))
                .build());
        return "redirect:/home";
    }
}


