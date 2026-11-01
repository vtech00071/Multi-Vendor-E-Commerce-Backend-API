package com.example.multi_vendor_ecommerce_api.globalexceptionhandlers;

import com.example.multi_vendor_ecommerce_api.Response.ErrorResponse;
import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.exceptions.InvalidEmailRuntimeExceptions;
import com.example.multi_vendor_ecommerce_api.exceptions.UserNameAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserServiceGlobalExceptionHandler {

    @ExceptionHandler(UserNameAlreadyExists.class)
    public ResponseEntity<Response> invalidEmailErrorHandler(UserNameAlreadyExists userNameAlreadyExists) {
        Response errorResponse = ErrorResponse
                .builder()
                .message(userNameAlreadyExists.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }

}
