package io.luankuhlmann.ecommerce.dto.response;

public record TokenResponse(
        String token,
        Long expiresIn
) {
}
