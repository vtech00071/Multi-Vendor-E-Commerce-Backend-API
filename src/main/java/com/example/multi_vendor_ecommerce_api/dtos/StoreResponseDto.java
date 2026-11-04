package com.example.multi_vendor_ecommerce_api.dtos;

import lombok.Builder;

@Builder
public record  StoreResponseDto(
        String name ,
        String description
) {
}
