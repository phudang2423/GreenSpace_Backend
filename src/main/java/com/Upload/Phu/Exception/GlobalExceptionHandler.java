package com.Upload.Phu.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        String message = errorCode.getMessage();

        if (ex.getFieldError() != null) {
            String enumKey = ex.getFieldError().getDefaultMessage();
            try {
                errorCode = ErrorCode.valueOf(enumKey);
                message = errorCode.getMessage();
            } catch (IllegalArgumentException e) {
                message = "Validation error: " + ex.getFieldError().getDefaultMessage();
            }
        }

        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getCode(),
                message,
                null,
                LocalDateTime.now()
        );
        log.warn("Validation error: {}", errorResponse);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INVALID_KEY.getCode(),
                "Missing required parameter: " + ex.getParameterName(),
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorcode().getCode(),
                ex.getMessage(),
                ex.getDetails(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
