package com.vijaya.user_service.exception;

import com.vijaya.user_service.payload.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception ex, WebRequest request){
       ExceptionResponse exceptionResponse = new ExceptionResponse(
               ex.getMessage(),
        request.getDescription(false),
        LocalDateTime.now()
       );

       return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
