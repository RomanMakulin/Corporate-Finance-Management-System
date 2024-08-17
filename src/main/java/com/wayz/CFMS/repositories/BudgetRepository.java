package com.wayz.CFMS.repositories;

import com.wayz.CFMS.models.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
}
