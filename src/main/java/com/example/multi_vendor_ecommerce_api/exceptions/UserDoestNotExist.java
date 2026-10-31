package com.example.multi_vendor_ecommerce_api.exceptions;

public class UserDoestNotExist extends RuntimeException {
    public UserDoestNotExist(String message) {
        super(message);
    }
}
