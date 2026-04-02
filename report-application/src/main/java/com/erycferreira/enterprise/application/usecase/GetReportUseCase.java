package com.erycferreira.enterprise.application.usecase;

import java.util.UUID;

import com.erycferreira.enterprise.domain.exception.ReportNotFoundException;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

public class GetReportUseCase {
  private final ReportRepository repository;

  public GetReportUseCase(ReportRepository repository) {
    this.repository = repository;
  }

  public Report execute(UUID id) {
    return repository.findById(id)
        .orElseThrow(() -> new ReportNotFoundException(id.toString()));
  }
}
