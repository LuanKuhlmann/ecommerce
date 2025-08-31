package io.luankuhlmann.ecommerce.dto.response;

public record UserCreatedResponse(
        Long id,
        String name,
        String email
) {
}
