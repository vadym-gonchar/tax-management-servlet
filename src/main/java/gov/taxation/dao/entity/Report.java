package gov.taxation.dao.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Report {
    private Long id;
    private LocalDate reportDate;
    private BigDecimal rate;
    private String comment;
    private LocalDateTime createdAt;
    private User user;
    private ReportStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
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

    public ReportStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ReportStatusEnum status) {
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
        return "Report{" +
                "id=" + id +
                ", reportDate='" + reportDate + '\'' +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", user=" + user +
                '}';
    }

    public static Report.Builder builder() {
        return new Report().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Report.Builder id(Long id) {
            Report.this.id = id;
            return this;
        }

        public Report.Builder reportDate(LocalDate reportDate) {
            Report.this.reportDate = reportDate;
            return this;
        }

        public Report.Builder rate(BigDecimal rate) {
            Report.this.rate = rate;
            return this;
        }

        public Report.Builder comment(String comment) {
            Report.this.comment = comment;
            return this;
        }

        public Report.Builder createdAt(LocalDateTime createdAt) {
            Report.this.createdAt = createdAt;
            return this;
        }

        public Report.Builder user(User user) {
            Report.this.user = user;
            return this;
        }

        public Report.Builder status(ReportStatusEnum status) {
            Report.this.status = status;
            return this;
        }

        public Report build() {
            return Report.this;
        }

    }
}

