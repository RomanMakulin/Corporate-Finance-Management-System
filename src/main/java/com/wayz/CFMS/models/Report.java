package com.wayz.CFMS.models;

import com.wayz.CFMS.models.subModels.BudgetCategories;
import com.wayz.CFMS.models.subModels.ReportType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Финансовый Отчет
 */
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Дата и время формирования отчета.
     */
    @Column(name = "date_time")
    private LocalDateTime reportDateTime;

    /**
     * Тип отчета (например, "Monthly", "Quarterly", "Annual", "Custom").
     */
    @Column(name = "report_type")
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    /**
     * Дата начала отчетного периода.
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;

    /**
     * Дата окончания отчетного периода.
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    /**
     * Сводка по бюджету — сумма, запланированная для каждой категории бюджета в отчетный период
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "report_budget_summary", joinColumns = @JoinColumn(name = "report_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "budget_amount")
    private Map<BudgetCategories, Double> budgetSummary;

    /**
     * Сводка по фактическим расходам — сумма расходов по каждой категории в отчетный период.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "report_expense_summary", joinColumns = @JoinColumn(name = "report_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "expense_amount")
    private Map<BudgetCategories, Double> expenseSummary;

    /**
     * Разница между запланированным бюджетом и фактическими расходами по каждой категории.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "report_variance_summary", joinColumns = @JoinColumn(name = "report_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "variance_amount")
    private Map<BudgetCategories, Double> varianceSummary;

    /**
     * Общая сумма бюджета на отчетный период.
     */
    @Column(name = "total_summary")
    private double totalSummary;

    /**
     * Общая сумма фактических расходов на отчетный период.
     */
    @Column(name = "total_expense")
    private double totalExpense;

    /**
     * Общее отклонение — разница между общим бюджетом и общими расходами.
     */
    @Column(name = "total_variance")
    private double totalVariance;

    /**
     * Автор отчета
     */
    @Column(name = "created_by")
    private String createdBy;

    public Report() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getReportDateTime() {
        return reportDateTime;
    }

    public void setReportDateTime(LocalDateTime reportDateTime) {
        this.reportDateTime = reportDateTime;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Map<BudgetCategories, Double> getBudgetSummary() {
        return budgetSummary;
    }

    public void setBudgetSummary(Map<BudgetCategories, Double> budgetSummary) {
        this.budgetSummary = budgetSummary;
    }

    public Map<BudgetCategories, Double> getExpenseSummary() {
        return expenseSummary;
    }

    public void setExpenseSummary(Map<BudgetCategories, Double> expenseSummary) {
        this.expenseSummary = expenseSummary;
    }

    public Map<BudgetCategories, Double> getVarianceSummary() {
        return varianceSummary;
    }

    public void setVarianceSummary(Map<BudgetCategories, Double> varianceSummary) {
        this.varianceSummary = varianceSummary;
    }

    public double getTotalSummary() {
        return totalSummary;
    }

    public void setTotalSummary(double totalSummary) {
        this.totalSummary = totalSummary;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getTotalVariance() {
        return totalVariance;
    }

    public void setTotalVariance(double totalVariance) {
        this.totalVariance = totalVariance;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Double.compare(totalSummary, report.totalSummary) == 0 && Double.compare(totalExpense, report.totalExpense) == 0 && Double.compare(totalVariance, report.totalVariance) == 0 && Objects.equals(id, report.id) && Objects.equals(reportDateTime, report.reportDateTime) && reportType == report.reportType && Objects.equals(startDate, report.startDate) && Objects.equals(endDate, report.endDate) && Objects.equals(budgetSummary, report.budgetSummary) && Objects.equals(expenseSummary, report.expenseSummary) && Objects.equals(varianceSummary, report.varianceSummary) && Objects.equals(createdBy, report.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reportDateTime, reportType, startDate, endDate, budgetSummary, expenseSummary, varianceSummary, totalSummary, totalExpense, totalVariance, createdBy);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportDateTime=" + reportDateTime +
                ", reportType=" + reportType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budgetSummary=" + budgetSummary +
                ", expenseSummary=" + expenseSummary +
                ", varianceSummary=" + varianceSummary +
                ", totalSummary=" + totalSummary +
                ", totalExpense=" + totalExpense +
                ", totalVariance=" + totalVariance +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
