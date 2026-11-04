package com.example.multi_vendor_ecommerce_api.services;


import com.example.multi_vendor_ecommerce_api.Response.Response;
import com.example.multi_vendor_ecommerce_api.Response.SuccessResponse;
import com.example.multi_vendor_ecommerce_api.dtos.AccountCreationDto;
import com.example.multi_vendor_ecommerce_api.dtos.PasswordUpdateDto;
import com.example.multi_vendor_ecommerce_api.dtos.UsersResponse;
import com.example.multi_vendor_ecommerce_api.exceptions.*;
import com.example.multi_vendor_ecommerce_api.mappers.UserDtoMapper;
import com.example.multi_vendor_ecommerce_api.entity.User;
import com.example.multi_vendor_ecommerce_api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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


    //this is to get the users by id

    public UsersResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoestNotExist("this users does not exist please try another id"));
        return UsersResponse
                .builder()
                .username(user.getUsername())
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRole())
                .createdAt(user.getCreateAt())
                .build();
    }


    //this is a function to update the user
    public Response updateEmail(Long id, AccountCreationDto newEmail) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        //here all the fields is stored into the list so that we can iterate into it and check it

        String email = newEmail.email();

        // 1. Direct, clean collection check instead of a stream
        if (email == null || email.isBlank()) {
            throw new BlankRole("empty fields please try again");
        }
        if (!email.matches(regex)) {
            throw new InvalidEmailRuntimeExceptions("invalid email please try again");
        }
        //what this does is that it saves the user into the database
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoestNotExist("this users does not exist please try another id"));

        user.setEmail(email);
        userRepository.save(user);

        return SuccessResponse.builder()
                .message("email updated successfully")
                .statusCode(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now())
                .build();
    }


    // this method is to update the password
    public Response updatePassword(Long id, PasswordUpdateDto newPassword) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoestNotExist("this user does not exist"));

        String password = newPassword.password();
        if (password == null || password.isBlank()) {
            throw new BlankRole("empty fields please try again");
        }
        if (password.length() < 6) {
            throw new InvalidPasswordRuntimeException("invalid password please try again");
        }

        user.setPassword(password);
        userRepository.save(user);

        return SuccessResponse.builder()
                .message("password updated successfully")
                .statusCode(HttpStatus.OK.value())
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public Response updateUsername(Long id, AccountCreationDto newUsername) {

        //first check if the user exists in the database by the id
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDoestNotExist("this user does not exist"));

        // now we will do the proper authentication and the bussiness logic
        String username = newUsername.username();

        if (username == null || username.isBlank()) {
            throw new BlankFieldException("blank field please try again");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            //how do they do that feature that helps suggests user
            throw new UserNameAlreadyExists(username + " already owned by a user try another one");
        }

        user.setUsername(username);
        userRepository.save(user);

        return SuccessResponse
                .builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .message("username updated successfully")
                .build();
    }

    public Response updateProfile(Long id, Map<String, Object> newUserUpdate) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserDoestNotExist("user does not exist please try again"));
        newUserUpdate.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(User.class, key);

            if (field != null) {
                field.setAccessible(true);

                ReflectionUtils.setField(field, existingUser, value);
            }

        });
        userRepository.save(existingUser);

        return SuccessResponse
                .builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.OK.value())
                .message("username updated successfully")
                .build();
    }

    public Response deleteUser(Long id) {
        //if the user is present delete the user account if not throw an error
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
        throw new UserDoestNotExist("this user does not exist please try again");

    }

    //this is to update the username

}
