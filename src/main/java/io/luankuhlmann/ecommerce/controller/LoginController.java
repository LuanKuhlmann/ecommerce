package io.luankuhlmann.ecommerce.controller;

import io.luankuhlmann.ecommerce.dto.request.LoginRequest;
import io.luankuhlmann.ecommerce.dto.response.LoginResponse;
import io.luankuhlmann.ecommerce.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse response = loginService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
