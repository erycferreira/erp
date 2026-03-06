package com.erycferreira.enterprise.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateReportRequest(
    @NotBlank(message="Report name is required") String name,
    @NotBlank(message="Report Type is required") String type
){}
