package com.erycferreira.enterprise.api.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.erycferreira.enterprise.domain.model.ReportStatus;
import com.erycferreira.enterprise.domain.model.ReportType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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

    protected ReportEntity() {
    }
}
