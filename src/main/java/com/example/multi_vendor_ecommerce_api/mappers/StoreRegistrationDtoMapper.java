package com.example.multi_vendor_ecommerce_api.mappers;

import com.example.multi_vendor_ecommerce_api.dtos.StoreRegistrationDto;
import com.example.multi_vendor_ecommerce_api.entity.VendorStore;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class StoreRegistrationDtoMapper implements Function<StoreRegistrationDto, VendorStore> {
    @Override
    public VendorStore apply(StoreRegistrationDto storeRegistrationDto) {
        return VendorStore
                .builder()
                .name(storeRegistrationDto.name())
                .description(storeRegistrationDto.description())
                .build();
    }
}
