package com.erycferreira.enterprise.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.erycferreira.enterprise.application.usecase.CreateReportUseCase;
import com.erycferreira.enterprise.application.usecase.StartReportProcessingUseCase;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

@Configuration
public class UseCaseConfig {

  @Bean
  public CreateReportUseCase createReportUseCase(ReportRepository repository) {
    return new CreateReportUseCase(repository);
  }

  @Bean
  public StartReportProcessingUseCase startReportProcessingUseCase(ReportRepository repository) {
    return new StartReportProcessingUseCase(repository);
  }
}
