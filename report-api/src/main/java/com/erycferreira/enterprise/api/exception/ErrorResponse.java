package com.erycferreira.enterprise.api.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatus() {
        return this.status;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}
