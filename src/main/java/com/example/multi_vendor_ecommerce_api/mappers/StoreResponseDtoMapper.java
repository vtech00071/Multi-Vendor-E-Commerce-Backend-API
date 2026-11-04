package com.example.multi_vendor_ecommerce_api.mappers;

import com.example.multi_vendor_ecommerce_api.dtos.StoreResponseDto;
import com.example.multi_vendor_ecommerce_api.models.VendorStore;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StoreResponseDtoMapper implements Function<VendorStore, StoreResponseDto> {
    @Override
    public StoreResponseDto apply(VendorStore vendorStore) {
        return StoreResponseDto.builder()
                .description(vendorStore.getDescription())
                .name(vendorStore.getName())
                .build();
    }
}
