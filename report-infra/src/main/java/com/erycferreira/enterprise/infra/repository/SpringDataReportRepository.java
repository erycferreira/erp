package com.erycferreira.enterprise.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erycferreira.enterprise.infra.entity.ReportEntity;

public interface SpringDataReportRepository
    extends JpaRepository<ReportEntity, UUID> {

}
