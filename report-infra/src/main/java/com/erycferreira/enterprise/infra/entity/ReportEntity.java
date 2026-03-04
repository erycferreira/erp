package com.erycferreira.enterprise.infra.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.erycferreira.enterprise.domain.model.ReportStatus;
import com.erycferreira.enterprise.domain.model.ReportType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reports")
public class ReportEntity {

  @Id
  private UUID id;

  private String name;

  @Enumerated(EnumType.STRING)
  private ReportType type;

  @Enumerated(EnumType.STRING)
  private ReportStatus status;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public ReportEntity(
      UUID id,
      String name,
      ReportType type,
      ReportStatus status,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  protected ReportEntity() {
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public ReportType getType() {
    return this.type;
  }

  public ReportStatus getStatus() {
    return this.status;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }
}
