package com.example.multi_vendor_ecommerce_api.services;

import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.dtos.StoreRegistrationDto;
import com.example.multi_vendor_ecommerce_api.dtos.StoreResponseDto;
import com.example.multi_vendor_ecommerce_api.enums.Roles;
import com.example.multi_vendor_ecommerce_api.exceptions.BlankFieldException;
import com.example.multi_vendor_ecommerce_api.exceptions.ForbiddenException;
import com.example.multi_vendor_ecommerce_api.exceptions.UserDoestNotExist;
import com.example.multi_vendor_ecommerce_api.mappers.StoreRegistrationDtoMapper;
import com.example.multi_vendor_ecommerce_api.mappers.StoreResponseDtoMapper;
import com.example.multi_vendor_ecommerce_api.models.User;
import com.example.multi_vendor_ecommerce_api.models.VendorStore;
import com.example.multi_vendor_ecommerce_api.repositories.StoreRepository;
import com.example.multi_vendor_ecommerce_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data

@AllArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreRegistrationDtoMapper storeRegistrationDtoMapper;
    private final VendorStore vendorStore;
    private final StoreResponseDtoMapper storeResponseDtoMapper;

    public Response registerStore(StoreRegistrationDto storeRegistrationDto) {

        List<String> fields = new ArrayList<>();
        fields.add(storeRegistrationDto.name());
        fields.add(storeRegistrationDto.description());

        if (fields.stream().anyMatch(String::isBlank)) {
            throw new BlankFieldException("field is blank please try again later");
        }

        //we want to check if the user that want to create the store exists
        User user = userRepository.findById(storeRegistrationDto.vendorId())
                .orElseThrow(() -> new UserDoestNotExist("this user does not exist please try again"));

        //we want to check if that user that you are trying to create a store is actually a vendor because if hes not a vendor he should not be able to cvreate a store
        if (!user.getRole().equals(Roles.VENDOR)) {
            throw new ForbiddenException("this user is not a vendor please try again");
        }

        //the next thing is that we need to check that the name of the vendor is approved by the admin but that i will add
        //that later its one of the feature that i want us to make sure that we need

        vendorStore.setVendor(user);
        vendorStore.setName(storeRegistrationDto.description());
        vendorStore.setDescription(storeRegistrationDto.name());


        storeRepository.save(vendorStore);
        return Response
                .builder()
                .message("store created successfully")
                .statusCode(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now())
                .build();

    }

    public List<StoreResponseDto> getAllStores() {
        return storeRepository
                .findAll()
                .stream()
                .map(storeResponseDtoMapper).toList();
    }
}
