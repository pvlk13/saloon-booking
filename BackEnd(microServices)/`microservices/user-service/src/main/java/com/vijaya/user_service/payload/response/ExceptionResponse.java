package com.vijaya.user_service.payload.response;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private String message;
    private String error;

    public ExceptionResponse(String message, String error, LocalDateTime timestamp) {
        this.message = message;
        this.error = error;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    private LocalDateTime timestamp;
}
