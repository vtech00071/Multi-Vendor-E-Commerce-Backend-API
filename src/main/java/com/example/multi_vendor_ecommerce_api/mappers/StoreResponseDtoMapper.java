package com.example.multi_vendor_ecommerce_api.mappers;

import com.example.multi_vendor_ecommerce_api.dtos.StoreResponseDto;
import com.example.multi_vendor_ecommerce_api.dtos.UsersResponse;
import com.example.multi_vendor_ecommerce_api.entity.User;
import com.example.multi_vendor_ecommerce_api.entity.VendorStore;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@AllArgsConstructor
@Component
public class StoreResponseDtoMapper implements Function<VendorStore, StoreResponseDto> {


    @Override
    public StoreResponseDto apply(VendorStore vendorStore) {

        User vendor = vendorStore.getVendor();
        return StoreResponseDto.builder()
                .description(vendorStore.getDescription())
                .name(vendorStore.getName())
                .vendor(UsersResponse
                        .builder()
                        .createdAt(vendor.getCreateAt())
                        .id(vendor.getId())
                        .username(vendor.getUsername())
                        .email(vendor.getEmail())
                        .roles(vendor.getRole())
                        .build())
                .build();
    }
}
