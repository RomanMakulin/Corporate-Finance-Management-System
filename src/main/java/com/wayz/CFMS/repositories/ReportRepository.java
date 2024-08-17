package com.wayz.CFMS.repositories;

import com.wayz.CFMS.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
