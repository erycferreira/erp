package com.erycferreira.enterprise.application.usecase;

import com.erycferreira.enterprise.application.port.ReportEventPort;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.model.ReportType;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

public class CreateReportUseCase {

  private final ReportRepository repository;
  private final ReportEventPort eventPort;

  public CreateReportUseCase(ReportRepository repository, ReportEventPort eventPort) {
    this.repository = repository;
    this.eventPort = eventPort;
  }

  public Report execute(String name, ReportType type) {
    Report report = new Report(name, type);
    Report saved = repository.save(report);
    eventPort.reportCreated(saved.getId());
    return saved;
  }
}
