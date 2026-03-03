package com.erycferreira.enterprise.api.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erycferreira.enterprise.api.dto.CreateReportRequest;
import com.erycferreira.enterprise.api.dto.ReportResponse;
import com.erycferreira.enterprise.application.usecase.CreateReportUseCase;
import com.erycferreira.enterprise.application.usecase.StartReportProcessingUseCase;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.model.ReportType;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final CreateReportUseCase createUseCase;
    private final StartReportProcessingUseCase startUseCase;

    public ReportController(
            CreateReportUseCase createUseCase,
            StartReportProcessingUseCase startUseCase
    ) {
        this.createUseCase = createUseCase;
        this.startUseCase = startUseCase;
    }

    @PostMapping
    public ReportResponse create(@RequestBody CreateReportRequest request) {
        Report report = createUseCase.execute(
                request.getName(),
                ReportType.valueOf(request.getType())
        );

        return toResponse(report);
    }

    @PostMapping("/{id}/start")
    public void start(@PathVariable("id") UUID id) {
        startUseCase.execute(id);
    }

    private ReportResponse toResponse(Report report) {
        return new ReportResponse(
                report.getId(),
                report.getName(),
                report.getType().name(),
                report.getStatus().name(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );
    }
}
