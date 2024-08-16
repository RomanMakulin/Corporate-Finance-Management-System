package com.wayz.CFMS.models;

import com.wayz.CFMS.models.subModels.BudgetCategories;
import com.wayz.CFMS.models.subModels.ReportType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "date_time")
    private LocalDateTime reportDateTime;

    @Column(name = "report_type")
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "report_budget_summary", joinColumns = @JoinColumn(name = "report_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "budget_amount")
    private Map<BudgetCategories, Double> budgetSummary;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "report_expense_summary", joinColumns = @JoinColumn(name = "report_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "expense_amount")
    private Map<BudgetCategories, Double> expenseSummary;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "report_variance_summary", joinColumns = @JoinColumn(name = "report_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "variance_amount")
    private Map<BudgetCategories, Double> varianceSummary;

}
