package com.example.multi_vendor_ecommerce_api.controllers;

import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.dtos.AccountCreationDto;
import com.example.multi_vendor_ecommerce_api.dtos.LoginRequestDto;
import com.example.multi_vendor_ecommerce_api.services.UserAuthService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Data
public class UserAuthController {

    private final UserAuthService userAuthService;

    //what this auth controller will have is only register and login
    @PostMapping("/api/auth/register")
    public ResponseEntity<Response> registerUser(@RequestBody AccountCreationDto userModel){
            return userAuthService.registerUser(userModel);
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequestDto user){
        return userAuthService.loginUser(user);
    }

}
