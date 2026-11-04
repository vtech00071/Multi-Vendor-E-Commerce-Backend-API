package com.example.multi_vendor_ecommerce_api.controllers;

import com.example.multi_vendor_ecommerce_api.dtos.StoreResponseDto;
import com.example.multi_vendor_ecommerce_api.models.VendorStore;
import com.example.multi_vendor_ecommerce_api.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class StoreController {

    //the reason why it didnt work was because there is not all argconstructor which is very important
    private final StoreService storeService;

    @GetMapping("/api/stores")
    public ResponseEntity<List<StoreResponseDto>> getAllStores(){
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(storeService.getAllStores());
    }
}
