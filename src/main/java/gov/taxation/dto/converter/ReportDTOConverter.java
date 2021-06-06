package gov.taxation.dto.converter;

import gov.taxation.dao.entity.Report;
import gov.taxation.dto.ReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReportDTOConverter {

    public static ReportDTO convert(Report report) {
        return ReportDTO.builder()
                .id(report.getId())
                .reportDate(report.getReportDate().toString())
                .rate(report.getRate())
                .comment(report.getComment())
                .createdAt(report.getCreatedAt())
                .status(report.getStatus().getName())
                .user(report.getUser())
                .build();
    }

    public static List<ReportDTO> convertList(List<Report> reports) {
        return reports.stream().map(d -> convert(d)).collect(Collectors.toList());
    }
}

