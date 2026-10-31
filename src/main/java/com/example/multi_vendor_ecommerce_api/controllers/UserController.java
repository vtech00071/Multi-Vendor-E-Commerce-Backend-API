package com.example.multi_vendor_ecommerce_api.controllers;

import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.Response.SuccessResponse;
import com.example.multi_vendor_ecommerce_api.dtos.AccountCreationDto;
import com.example.multi_vendor_ecommerce_api.dtos.UsersResponse;
import com.example.multi_vendor_ecommerce_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //what this method will do is that it will return all the users and their roles
    @GetMapping("/api/auth/login")
    public ResponseEntity<List<UsersResponse>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @PatchMapping("/api/users/{id}")
    public ResponseEntity<Response> updateUserById(@PathVariable Long id, AccountCreationDto updatedUser) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUserById(id,updatedUser));
    }

}
