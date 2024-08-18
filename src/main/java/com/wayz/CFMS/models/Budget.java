package com.wayz.CFMS.models;

import com.wayz.CFMS.models.subModels.BudgetCategories;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Сущность планируемых расходов - выделяемый бюджет на что-либо
 */
@Entity
@Table (name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Наименование бюджета
     */
    private String name;

    /**
     * Планируемая сумма бюджета
     */
    private double amount;

    /**
     * Категория бюджета
     */
    @Enumerated(EnumType.STRING)
    private BudgetCategories category;

    /**
     * Дата начала бюджета
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;

    /**
     * Дата окончания бюджета
     */
    @Column(name = "end_date")
    private LocalDateTime endDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BudgetCategories getCategory() {
        return category;
    }

    public void setCategory(BudgetCategories budgetCategories) {
        this.category = budgetCategories;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Double.compare(amount, budget.amount) == 0 && Objects.equals(id, budget.id) && Objects.equals(name, budget.name) && category == budget.category && Objects.equals(startDate, budget.startDate) && Objects.equals(endDate, budget.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, category, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", categories=" + category +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
