package com.erycferreira.enterprise.application.usecase;

import java.util.UUID;

import com.erycferreira.enterprise.domain.exception.ReportNotFoundException;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

public class ProcessReportUseCase {
  private final ReportRepository repository;

  public ProcessReportUseCase(ReportRepository repository) {
    this.repository = repository;
  }

  public void execute(UUID id) {
    Report report = repository.findById(id)
        .orElseThrow(() -> new ReportNotFoundException(id.toString()));

    report.startProcessing();
    repository.save(report);

    try {
      // simulando geração de relatorio
      Thread.sleep(10000);
      report.complete();
    } catch (Exception e) {
      report.fail();
    } finally {
      repository.save(report);
    }
  }
}
