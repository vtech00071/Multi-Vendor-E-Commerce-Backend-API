package com.example.multi_vendor_ecommerce_api.controllers;

import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.Response.SuccessResponse;
import com.example.multi_vendor_ecommerce_api.dtos.AccountCreationDto;
import com.example.multi_vendor_ecommerce_api.dtos.PasswordUpdateDto;
import com.example.multi_vendor_ecommerce_api.dtos.UsersResponse;
import com.example.multi_vendor_ecommerce_api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //what this method will do is that it will return all the users and their roles
    @GetMapping("/api/users")
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

    //this is the endpoint to edit the email
    @PatchMapping("/api/users/email/{id}")
    public ResponseEntity<Response> updateEmail(@PathVariable Long id, @RequestBody AccountCreationDto newEmail) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateEmail(id,newEmail));
    }


    @PatchMapping("/api/users/password/{id}")
    public ResponseEntity<Response> updatePassword(@PathVariable Long id, @RequestBody PasswordUpdateDto newPassword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updatePassword(id,newPassword));
    }


    @PatchMapping("/api/users/username/{id}")
    public ResponseEntity<Response> updateUsername(@PathVariable Long id, @RequestBody AccountCreationDto newUsername) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUsername(id,newUsername));
    }

    //this is the dynamic merge
    @PatchMapping("/api/users/{id}")
    public ResponseEntity<Response> updateProfile(@PathVariable Long id, @RequestBody Map<String , Object> newUserUpdate) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateProfile(id,newUserUpdate));
    }


    @DeleteMapping("/api/users/{id} ")
    public ResponseEntity<Response> updateProfile(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.deleteUser(id));
    }




}
