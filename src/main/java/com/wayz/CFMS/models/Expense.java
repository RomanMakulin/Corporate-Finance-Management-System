package com.wayz.CFMS.models;

import com.wayz.CFMS.models.subModels.Categories;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Сущность фактических расходов
 */
@Entity
@Table(name = "expense")
public class Expense{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime date;

    private double amount;

    @Enumerated(EnumType.STRING)
    private Categories category;

    private String description;

    @OneToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id")
    private Budget budget;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(amount, expense.amount) == 0 && Objects.equals(id, expense.id) && Objects.equals(date, expense.date) && category == expense.category && Objects.equals(description, expense.description) && Objects.equals(budget, expense.budget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount, category, description, budget);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                '}';
    }

}
