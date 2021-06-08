package gov.taxation.command;

import gov.taxation.dao.DBException;
import gov.taxation.dao.entity.ReportStatusEnum;
import gov.taxation.dao.entity.RoleType;
import gov.taxation.dao.entity.User;
import gov.taxation.dto.Page;
import gov.taxation.dto.ReportDTO;
import gov.taxation.service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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

        User user = CommandUtility.getUserFromSession(request);

        Page pageable;
        try {
            pageable = reportService.getAllPageable(pageNo, sort, direct, user);
        } catch (DBException e) {
            logger.info(e.getMessage());
            request.setAttribute("errorMsg", e.getMessage());
            return "/WEB-INF/home.jsp";
        }

        if (user.getRole() == RoleType.ROLE_USER){
            List<ReportDTO> reports = pageable.getReportDTO().stream().filter(report ->
                    report.getUser().getUserName().equals(user.getUserName())).collect(Collectors.toList());
            pageable = Page.builder().reports(reports).build();
        }

        request.setAttribute("reportItems", pageable.getReportDTO());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("totalPages", pageable.getTotalPages());
        request.setAttribute("sort", sort);
        request.setAttribute("direct", direct);
  //      request.setAttribute("directTable", (direct.equals("ASC")) ? "DESC" : "ASC");
        request.setAttribute("statuses", ReportStatusEnum.values());
        request.setAttribute("status", status);

        return "/WEB-INF/home.jsp";
    }
}

