package gov.taxation.dto;

import gov.taxation.dao.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReportDTO {
    private Long id;
    private String reportDate;
    private BigDecimal rate;
    private String comment;
    private LocalDateTime createdAt;
    private String status;
    private User user;

    public ReportDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "id=" + id +
                ", reportDate='" + reportDate + '\'' +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }

    public static ReportDTO.Builder builder() {
        return new ReportDTO().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public ReportDTO.Builder id(Long id) {
            ReportDTO.this.id = id;
            return this;
        }

        public ReportDTO.Builder reportDate(String reportDate) {
            ReportDTO.this.reportDate = reportDate;
            return this;
        }

        public ReportDTO.Builder rate(BigDecimal rate) {
            ReportDTO.this.rate = rate;
            return this;
        }

        public ReportDTO.Builder comment(String comment) {
            ReportDTO.this.comment = comment;
            return this;
        }

        public ReportDTO.Builder createdAt(LocalDateTime createdAt) {
            ReportDTO.this.createdAt = createdAt;
            return this;
        }

        public ReportDTO.Builder status(String status) {
            ReportDTO.this.status = status;
            return this;
        }

        public ReportDTO.Builder user(User user) {
            ReportDTO.this.user = user;
            return this;
        }

        public ReportDTO build() {
            return ReportDTO.this;
        }

    }
}

