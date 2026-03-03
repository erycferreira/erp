package com.erycferreira.enterprise.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Report {

    private final UUID id;
    private String name;
    private final ReportType type;
    private ReportStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Report(
            UUID id,
            String name,
            ReportType type,
            ReportStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Report(String name, ReportType type) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.status = ReportStatus.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void startProcessing() {
        if (this.status != ReportStatus.CREATED) {
            throw new IllegalStateException("Report cannot start processing from status: " + this.status);
        }

        this.status = ReportStatus.PROCESSING;
        this.updatedAt = LocalDateTime.now();
    }

    public void complete() {
        if (this.status != ReportStatus.PROCESSING) {
            throw new IllegalStateException("Report can only be completed if processing");
        }

        this.status = ReportStatus.COMPLETED;
        this.updatedAt = LocalDateTime.now();
    }

    public void fail() {
        if (this.status != ReportStatus.PROCESSING) {
            throw new IllegalStateException("Report can only fail if processing");
        }

        this.status = ReportStatus.FAILED;
        this.updatedAt = LocalDateTime.now();
    }

    public static Report restore(
            UUID id,
            String name,
            ReportType type,
            ReportStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new Report(id, name, type, status, createdAt, updatedAt);
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
