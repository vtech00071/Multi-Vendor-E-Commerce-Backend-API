package com.example.multi_vendor_ecommerce_api.globalexceptionhandlers;

import com.example.multi_vendor_ecommerce_api.Response.ErrorResponse;
import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
//that means anytime there is an exception it comes here
public class AuthExceptionHandler {

    @ExceptionHandler(InvalidEmailRuntimeExceptions.class)
    public ResponseEntity<Response> invalidEmailErrorHandler(InvalidEmailRuntimeExceptions invalidEmailRuntimeExceptions) {
        Response errorResponse = ErrorResponse
                .builder()
                .message(invalidEmailRuntimeExceptions.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }

    //this means whenever the exceptions is thrown catch it here and make the most of it
    @ExceptionHandler(InvalidPasswordRuntimeException.class)
    public ResponseEntity<Response> invalidPassword(InvalidPasswordRuntimeException invalidPasswordRuntimeException) {
        Response errorResponse = ErrorResponse
                .builder()
                .message(invalidPasswordRuntimeException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }

    @ExceptionHandler(BlankFieldException.class)
    public ResponseEntity<Response> invalidPassword(BlankFieldException blankFieldException) {
        Response errorResponse = ErrorResponse
                .builder()
                .message(blankFieldException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<Response> invalidRole(InvalidRoleException invalidRoleException) {
        Response errorResponse = ErrorResponse
                .builder()
                .message(invalidRoleException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }

    @ExceptionHandler(BlankRole.class)
    public ResponseEntity<Response> blankRole(BlankRole blankRole) {
        Response response = ErrorResponse
                .builder()
                .message(blankRole.getMessage())
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> invalidRole() {
        Response errorResponse = ErrorResponse
                .builder()
                .message("invalid role please try again")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);

    }
}
