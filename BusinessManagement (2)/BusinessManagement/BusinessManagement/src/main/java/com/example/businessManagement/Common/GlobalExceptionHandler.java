package com.example.businessManagement.Common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


    @RestControllerAdvice
    public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handle(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, error, null));
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleMalformedRequest(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(false, "Malformed JSON request. Please check your request body.", null));
    }
}


