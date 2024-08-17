package com.wayz.CFMS.repositories;

import com.wayz.CFMS.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
