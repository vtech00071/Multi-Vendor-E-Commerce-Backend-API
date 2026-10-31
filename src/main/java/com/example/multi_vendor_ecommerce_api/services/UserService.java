package com.example.multi_vendor_ecommerce_api.services;

import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.Response.SuccessResponse;
import com.example.multi_vendor_ecommerce_api.dtos.AccountCreationDto;
import com.example.multi_vendor_ecommerce_api.dtos.UsersResponse;
import com.example.multi_vendor_ecommerce_api.enums.Roles;
import com.example.multi_vendor_ecommerce_api.exceptions.*;
import com.example.multi_vendor_ecommerce_api.mappers.UserDtoMapper;
import com.example.multi_vendor_ecommerce_api.models.User;
import com.example.multi_vendor_ecommerce_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public List<UsersResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userDtoMapper)
                .toList();

    }

    public UsersResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoestNotExist("this users does not exist please try another id"));
        return UsersResponse.builder()
                .username(user.getUsername())
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRole())
                .createdAt(user.getCreateAt())
                .build();
    }


    //this is a function to update the user

    public Response updateUserById(Long id, AccountCreationDto updatedUser) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        //here all the fields is stored into the list so that we can iterate into it and check it

        String username = updatedUser.username();
        String email = updatedUser.email();
        String password = updatedUser.password();
        Roles role = updatedUser.role();

        List<String> fieldsToCheck = List.of(
                username,
                email,
                password
        );

        List<Roles> roles = new ArrayList<>();
        roles.add(Roles.ADMIN);
        roles.add(Roles.VENDOR);
        roles.add(Roles.CUSTOMER);


        // 1. Direct, clean collection check instead of a stream
        if (!roles.contains(role)) {
            throw new InvalidRoleException("invalid Role try Vendor Customer or Admin");
        }
        // 2. Check for blank fields first (fail-fast principle)
        if (fieldsToCheck.stream().anyMatch(f -> f == null || f.trim().isBlank())) {
            throw new BlankRole("empty fields please try again");
        }
        if (!email.matches(regex)) {
            throw new InvalidEmailRuntimeExceptions("invalid email please try again");
        }
        if (password.length() < 6) {
            throw new InvalidPasswordRuntimeException("invalid password please try again");
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoestNotExist("this users does not exist please try another id"));
        user.setUsername(updatedUser.username());
        user.setEmail(updatedUser.password());
        user.setPassword(updatedUser.password());
        user.setRole(updatedUser.role());

        userRepository.save(user);

        return SuccessResponse
                .builder()
                .message("update successfully")
                .statusCode(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now())
                .build();


    }
}
