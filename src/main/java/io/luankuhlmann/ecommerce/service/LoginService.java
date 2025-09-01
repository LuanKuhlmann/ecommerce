package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.dto.request.LoginRequest;
import io.luankuhlmann.ecommerce.dto.response.LoginResponse;
import io.luankuhlmann.ecommerce.dto.response.TokenResponse;
import io.luankuhlmann.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginResponse login(final LoginRequest loginRequest) {
        User user = userService.getByEmail(loginRequest.email());

        validatePassword(user.getPassword(), loginRequest.password());

        TokenResponse accessTokenResponse = tokenService.createAccessToken(user);
        TokenResponse refreshTokenResponse = tokenService.createRefreshToken(user);

        return new LoginResponse(accessTokenResponse.token(), accessTokenResponse.expiresIn(),
                refreshTokenResponse.token(), refreshTokenResponse.expiresIn());
    }

    private void validatePassword(String userPassword, String requestPassword) {
        if (!passwordEncoder.matches(userPassword, requestPassword)) {
            throw new RuntimeException("Senha inválida.");
        }
    }
}
