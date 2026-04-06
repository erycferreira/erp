package com.erycferreira.enterprise.api.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erycferreira.enterprise.api.dto.CreateReportRequest;
import com.erycferreira.enterprise.api.dto.ReportResponse;
import com.erycferreira.enterprise.api.mapper.ReportMapper;
import com.erycferreira.enterprise.application.usecase.CreateReportUseCase;
import com.erycferreira.enterprise.application.usecase.GetReportUseCase;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.model.ReportType;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
@Validated
public class ReportController {

  private final GetReportUseCase getUseCase;
  private final CreateReportUseCase createUseCase;
  private final ReportMapper mapper;
  private final static Logger log = LoggerFactory.getLogger(ReportController.class);

  public ReportController(
      GetReportUseCase getUseCase,
      CreateReportUseCase createUseCase,
      ReportMapper mapper) {
    this.getUseCase = getUseCase;
    this.createUseCase = createUseCase;
    this.mapper = mapper;
  }

  @GetMapping("/{id}")
  @WithSpan("get-report-endpoint")
  public ReportResponse get(@PathVariable("id") UUID id) {
    Report report = getUseCase.execute(id);
    return mapper.toResponse(report);
  }

  @PostMapping
  @WithSpan("create-report-endpoint")
  public ReportResponse create(
      @Valid @RequestBody CreateReportRequest request) {

    log.info("Creating report name={} type={}", request.name(), request.type());
    Report report = createUseCase.execute(
        request.name(),
        ReportType.valueOf(request.type()));

    return mapper.toResponse(report);
  }
}
