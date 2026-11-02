package com.example.multi_vendor_ecommerce_api.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
