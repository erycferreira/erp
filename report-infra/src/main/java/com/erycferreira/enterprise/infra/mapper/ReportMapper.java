package com.erycferreira.enterprise.infra.mapper;

import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.infra.entity.ReportEntity;

public class ReportMapper {

  public static ReportEntity toEntity(Report report) {
    ReportEntity entity = new ReportEntity(
        report.getId(),
        report.getName(),
        report.getType(),
        report.getStatus(),
        report.getCreatedAt(),
        report.getUpdatedAt());

    return entity;
  }

  public static Report toDomain(ReportEntity entity) {
    return Report.restore(
        entity.getId(),
        entity.getName(),
        entity.getType(),
        entity.getStatus(),
        entity.getCreatedAt(),
        entity.getUpdatedAt());
  }

}
