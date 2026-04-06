package com.erycferreira.enterprise.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.erycferreira.enterprise.application.port.ReportEventPort;
import com.erycferreira.enterprise.application.usecase.CreateReportUseCase;
import com.erycferreira.enterprise.application.usecase.GetReportUseCase;
import com.erycferreira.enterprise.application.usecase.ProcessReportUseCase;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

@Configuration
public class UseCaseConfig {

  @Bean
  public GetReportUseCase getReportUseCase(ReportRepository repository) {
    return new GetReportUseCase(repository);
  }

  @Bean
  public CreateReportUseCase createReportUseCase(ReportRepository repository, ReportEventPort eventPort) {
    return new CreateReportUseCase(repository, eventPort);
  }

  @Bean
  public ProcessReportUseCase processReportUseCase(ReportRepository repository) {
    return new ProcessReportUseCase(repository);
  }
}
