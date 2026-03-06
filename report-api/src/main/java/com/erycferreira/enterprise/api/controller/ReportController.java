package com.erycferreira.enterprise.api.controller;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erycferreira.enterprise.api.dto.CreateReportRequest;
import com.erycferreira.enterprise.api.dto.ReportResponse;
import com.erycferreira.enterprise.api.mapper.ReportMapper;
import com.erycferreira.enterprise.application.usecase.CreateReportUseCase;
import com.erycferreira.enterprise.application.usecase.StartReportProcessingUseCase;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.model.ReportType;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
@Validated
public class ReportController {

  private final CreateReportUseCase createUseCase;
  private final StartReportProcessingUseCase startUseCase;
  private final ReportMapper mapper;

  public ReportController(
      CreateReportUseCase createUseCase,
      StartReportProcessingUseCase startUseCase,
      ReportMapper mapper) {
    this.createUseCase = createUseCase;
    this.startUseCase = startUseCase;
    this.mapper = mapper;
  }

  @PostMapping
  public ReportResponse create(
      @Valid @RequestBody CreateReportRequest request) {

    Report report = createUseCase.execute(
        request.name(),
        ReportType.valueOf(request.type()));

    return mapper.toResponse(report);
  }

  @PostMapping("/{id}/start")
  public void start(@PathVariable("id") UUID id) {
    startUseCase.execute(id);
  }
}
