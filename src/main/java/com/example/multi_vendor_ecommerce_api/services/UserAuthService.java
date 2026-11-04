package com.example.multi_vendor_ecommerce_api.services;

import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.Response.SuccessResponse;
import com.example.multi_vendor_ecommerce_api.dtos.AccountCreationDto;
import com.example.multi_vendor_ecommerce_api.dtos.LoginRequestDto;
import com.example.multi_vendor_ecommerce_api.enums.Roles;
import com.example.multi_vendor_ecommerce_api.exceptions.*;
import com.example.multi_vendor_ecommerce_api.mappers.AccountCreationDtoMapper;
import com.example.multi_vendor_ecommerce_api.models.User;
import com.example.multi_vendor_ecommerce_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
//what this data does is getters and setters
@Data
public class UserAuthService {
    static Response successResponse = SuccessResponse
            .builder()
            .message("success")
            .statusCode(HttpStatus.OK.value())
            .timeStamp(LocalDateTime.now())
            .build();
    private final UserRepository userRepository;
    private final AccountCreationDtoMapper accountCreationDtoMapper;


    //this is the account creation method
    public ResponseEntity<Response> registerUser(AccountCreationDto accountCreationDto) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        //here all the fields is stored into the list so that we can iterate into it and check it

        String username = accountCreationDto.username();
        String email = accountCreationDto.email();
        String password = accountCreationDto.password();
        Roles role = accountCreationDto.role();

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
        } else {

            //this accountCreation is the parameter account creation Dto
            User user = accountCreationDtoMapper.apply(accountCreationDto);
            userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(successResponse);
        }
    }


    //this is the login authentication method
    public ResponseEntity<Response> loginUser(LoginRequestDto user) {

        String username = user.username();
        String password = user.password();
        List<String> fieldsToCheck = List.of(
                username,
                password

        );
        if (fieldsToCheck.stream().anyMatch(f -> f.trim().isBlank())) {
            throw new BlankFieldException("enter your username and password please");
        }

        //even if the result does not exist we are catching the error so no problem but just incase let me use optional
        Optional<User> userModel = Optional.of(userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUsernameOrUserDoestNotExist("invalid username or user does not exist")));

        if (!userModel.get().getPassword().equals(password)) {
            throw new InvalidPasswordRuntimeException("invalid password please try again");
        }
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);

    }
}
