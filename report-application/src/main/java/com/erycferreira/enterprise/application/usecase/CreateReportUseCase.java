package com.erycferreira.enterprise.application.usecase;

import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.model.ReportType;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

public class CreateReportUseCase {

    private final ReportRepository repository;

    public CreateReportUseCase(ReportRepository repository) {
        this.repository = repository;
    }

    public Report execute(String name, ReportType type) {
        Report report = new Report(name, type);

        return repository.save(report);
    }
}
