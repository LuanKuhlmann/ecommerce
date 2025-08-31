package io.luankuhlmann.ecommerce.controller;

import io.luankuhlmann.ecommerce.dto.request.UserRequest;
import io.luankuhlmann.ecommerce.dto.response.UserCreatedResponse;
import io.luankuhlmann.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserCreatedResponse> createOrder(@RequestBody @Valid UserRequest userRequest) {
        UserCreatedResponse response = userService.createUser(userRequest);
        return ResponseEntity.created(URI.create("/user/")).body(response);
    }
}
