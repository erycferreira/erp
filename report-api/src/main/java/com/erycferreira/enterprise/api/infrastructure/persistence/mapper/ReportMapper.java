package com.erycferreira.enterprise.api.infrastructure.persistence.mapper;

import com.erycferreira.enterprise.api.infrastructure.persistence.entity.ReportEntity;
import com.erycferreira.enterprise.domain.model.Report;

public class ReportMapper {

    public static ReportEntity toEntity(Report report) {
        ReportEntity entity = new ReportEntity(
                report.getId(),
                report.getName(),
                report.getType(),
                report.getStatus(),
                report.getCreatedAt(),
                report.getUpdatedAt()
        );

        return entity;
    }

    public static Report toDomain(ReportEntity entity) {
        return Report.restore(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

}
