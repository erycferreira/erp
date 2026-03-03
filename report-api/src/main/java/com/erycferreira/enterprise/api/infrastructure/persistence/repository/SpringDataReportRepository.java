package com.erycferreira.enterprise.api.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erycferreira.enterprise.api.infrastructure.persistence.entity.ReportEntity;

public interface SpringDataReportRepository
        extends JpaRepository<ReportEntity, UUID> {

}
