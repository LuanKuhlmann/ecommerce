package io.luankuhlmann.ecommerce.mapper;

import io.luankuhlmann.ecommerce.dto.request.UserRequest;
import io.luankuhlmann.ecommerce.dto.response.UserCreatedResponse;
import io.luankuhlmann.ecommerce.model.User;

public class UserMapper {

    public static User toEntity(UserRequest userRequest, String encodedPassword) {
        return User.builder()
                .email(userRequest.email())
                .name(userRequest.name())
                .password(encodedPassword)
                .build();
    }

    public static UserCreatedResponse toUserCreatedResponse(User user){
        return new UserCreatedResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
