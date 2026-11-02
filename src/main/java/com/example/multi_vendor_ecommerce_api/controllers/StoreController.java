package com.example.multi_vendor_ecommerce_api.controllers;

import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.dtos.StoreRegistrationDto;
import com.example.multi_vendor_ecommerce_api.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
public class StoreController {

    //the reason why it didnt work was because there is not all argconstructor which is very important
    private final StoreService storeService;


    @PostMapping("/api/stores/register")
    public ResponseEntity<Response> registerStore(@RequestBody StoreRegistrationDto storeRegistrationDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storeService.registerStore(storeRegistrationDto));

    }
}
