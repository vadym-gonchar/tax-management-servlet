package gov.taxation.dto;

import java.util.List;

public class Page {
    List<ReportDTO> reportDTO;
    Long totalPages;

    public List<ReportDTO> getReportDTO() {
        return reportDTO;
    }

    public void setReportDTO(List<ReportDTO> reports) {
        this.reportDTO = reports;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public static Page.Builder builder() {
        return new Page().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Page.Builder reports(List<ReportDTO> reports) {
            Page.this.reportDTO = reports;
            return this;
        }

        public Page.Builder totalPages(Long totalPages) {
            Page.this.totalPages = totalPages;
            return this;
        }

        public Page build() {
            return Page.this;
        }
    }
}

