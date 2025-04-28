package com.Upload.Phu.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int code;
    private String message;
    private Object details;
    private LocalDateTime timestamp;

    // Constructor đầy đủ
    public ErrorResponse(int code, String message, Object details, LocalDateTime timestamp) {
        this.code = code;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    // Getters và setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Optional: override toString() nếu bạn dùng log.warn(...) với {}
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", details=" + details +
                ", timestamp=" + timestamp +
                '}';
    }
}
