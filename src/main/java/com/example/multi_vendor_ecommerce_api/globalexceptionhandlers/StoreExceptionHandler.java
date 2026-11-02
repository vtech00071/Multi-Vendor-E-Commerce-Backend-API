package com.example.multi_vendor_ecommerce_api.globalexceptionhandlers;

import com.example.multi_vendor_ecommerce_api.Response.ErrorResponse;
import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.exceptions.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class StoreExceptionHandler {

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Response> userNotAllowedException(ForbiddenException forbiddenException){
        Response errorResponse = ErrorResponse
                .builder()
                .message(forbiddenException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
