package com.erycferreira.enterprise.infra.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.erycferreira.enterprise.infra.entity.ReportEntity;
import com.erycferreira.enterprise.infra.mapper.ReportMapper;
import com.erycferreira.enterprise.infra.repository.SpringDataReportRepository;
import com.erycferreira.enterprise.domain.model.Report;
import com.erycferreira.enterprise.domain.repository.ReportRepository;

@Repository
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
