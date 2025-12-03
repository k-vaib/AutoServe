package com.car_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.car_backend.dto.ApiResponse;

@RestControllerAdvice // This makes it listen for exceptions across all Controllers
public class GlobalExceptionHandler {

    // 1. Handle our custom exception (e.g., if we throw this when email exists)
    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ApiResponse> handleResourceAlreadyExists(ResourceAlreadyExists e) {
        // Returns 409 Conflict
        ApiResponse response = new ApiResponse(e.getMessage(), "Registration failed due to duplicate entry.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    
    // 2. Handle 404 Not Found (If, for example, a User is not found by ID)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException e) {
        // Returns 404 Not Found
        ApiResponse response = new ApiResponse(e.getMessage(), "Resource not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 3. Handle unexpected server errors (The catch-all)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleGenericRuntimeException(RuntimeException e) {
        // Returns 500 Internal Server Error
        ApiResponse response = new ApiResponse(e.getMessage(), "Internal Server Error.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}