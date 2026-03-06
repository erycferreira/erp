package com.erycferreira.enterprise.api.mapper;

import org.springframework.stereotype.Component;

import com.erycferreira.enterprise.api.dto.ReportResponse;
import com.erycferreira.enterprise.domain.model.Report;

@Component
public class ReportMapper {

  public ReportResponse toResponse(Report report) {
    return new ReportResponse(
        report.getId(),
        report.getName(),
        report.getType().name(),
        report.getStatus().name(),
        report.getCreatedAt(),
        report.getUpdatedAt());
  }
}
