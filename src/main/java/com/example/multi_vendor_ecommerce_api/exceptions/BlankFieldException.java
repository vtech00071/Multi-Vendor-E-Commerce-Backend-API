package com.example.multi_vendor_ecommerce_api.exceptions;

public class BlankFieldException extends RuntimeException {
    public BlankFieldException(String message) {
        super(message);
    }
}
