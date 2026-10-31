package com.example.multi_vendor_ecommerce_api.mappers;

import com.example.multi_vendor_ecommerce_api.dtos.AccountCreationDto;
import com.example.multi_vendor_ecommerce_api.enums.Roles;
import com.example.multi_vendor_ecommerce_api.models.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AccountCreationDtoMapper implements Function<AccountCreationDto, User> {


    @Override
    public User apply(AccountCreationDto accountCreationDto) {

        User userModel = new User();
        userModel.setUsername(accountCreationDto.username());
        userModel.setEmail(accountCreationDto.email());
        userModel.setPassword(accountCreationDto.password());
        userModel.setRole(Roles.valueOf(String.valueOf(accountCreationDto.role())));

        return userModel;
    }
}
