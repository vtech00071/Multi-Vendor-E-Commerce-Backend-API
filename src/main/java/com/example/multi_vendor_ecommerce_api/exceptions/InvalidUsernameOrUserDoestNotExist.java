package com.example.multi_vendor_ecommerce_api.exceptions;

public class InvalidUsernameOrUserDoestNotExist extends RuntimeException {
    public InvalidUsernameOrUserDoestNotExist(String message) {
        super(message);
    }
}
