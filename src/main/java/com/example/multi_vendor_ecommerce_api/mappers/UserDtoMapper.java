package com.example.multi_vendor_ecommerce_api.mappers;

import com.example.multi_vendor_ecommerce_api.dtos.UsersResponse;
import com.example.multi_vendor_ecommerce_api.models.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component

public class UserDtoMapper implements Function<User, UsersResponse> {

    @Override
    public UsersResponse apply(User user) {
        return new UsersResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreateAt(),
                user.getRole()
        );
    }
}
