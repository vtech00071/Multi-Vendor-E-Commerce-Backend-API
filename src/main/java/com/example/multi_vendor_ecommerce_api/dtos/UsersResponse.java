package com.example.multi_vendor_ecommerce_api.dtos;

import com.example.multi_vendor_ecommerce_api.enums.Roles;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record UsersResponse(
        Long id,
        String username,
        String email,
        LocalDateTime createdAt,
        Roles roles


) {
}
