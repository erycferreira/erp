package com.erycferreira.enterprise.application.usecase;

import java.util.UUID;

import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

public class StartReportProcessingUseCase {

    private final ReportRepository repository;

    public StartReportProcessingUseCase(ReportRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID reportId) {
        Report report = repository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("Report not found"));

        report.startProcessing();

        repository.save(report);
    }
}
