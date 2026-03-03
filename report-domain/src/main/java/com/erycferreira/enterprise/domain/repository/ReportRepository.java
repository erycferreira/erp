package com.erycferreira.enterprise.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.erycferreira.enterprise.domain.model.Report;

public interface ReportRepository {

    Report save(Report report);

    Optional<Report> findById(UUID id);
}
