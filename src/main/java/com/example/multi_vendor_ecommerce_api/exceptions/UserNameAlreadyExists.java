package com.example.multi_vendor_ecommerce_api.exceptions;

public class UserNameAlreadyExists extends RuntimeException {
    public UserNameAlreadyExists(String message) {
        super(message);
    }
}
