package com.example.multi_vendor_ecommerce_api.exceptions;

public class InvalidPasswordRuntimeException extends RuntimeException {
    public InvalidPasswordRuntimeException(String message) {
        super(message);
    }
}
