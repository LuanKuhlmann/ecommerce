package io.luankuhlmann.ecommerce.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
