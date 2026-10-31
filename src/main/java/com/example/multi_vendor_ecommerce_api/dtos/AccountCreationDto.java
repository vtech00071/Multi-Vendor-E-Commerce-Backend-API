package com.example.multi_vendor_ecommerce_api.dtos;

import com.example.multi_vendor_ecommerce_api.enums.Roles;
import org.springframework.stereotype.Service;


public record AccountCreationDto(
        String username,
        String email,
        String password,
        Roles  role

) {
}
