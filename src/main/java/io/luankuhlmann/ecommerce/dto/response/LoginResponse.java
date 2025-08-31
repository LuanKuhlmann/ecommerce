package io.luankuhlmann.ecommerce.dto.response;

public record LoginResponse(
        String token,
        Long expiresIn,
        String refreshToken,
        Long refreshExpiresIn) {
}
