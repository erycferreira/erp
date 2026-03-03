package com.erycferreira.enterprise.api.infrastructure.persistence.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.erycferreira.enterprise.api.infrastructure.persistence.entity.ReportEntity;
import com.erycferreira.enterprise.api.infrastructure.persistence.mapper.ReportMapper;
import com.erycferreira.enterprise.api.infrastructure.persistence.repository.SpringDataReportRepository;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

@Component
public class ReportRepositoryImpl implements ReportRepository {

    private final SpringDataReportRepository jpaRepository;

    public ReportRepositoryImpl(SpringDataReportRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Report save(Report report) {
        ReportEntity entity = ReportMapper.toEntity(report);
        ReportEntity saved = jpaRepository.save(entity);
        return ReportMapper.toDomain(saved);
    }

    @Override
    public Optional<Report> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(ReportMapper::toDomain);
    }
}
