package io.luankuhlmann.ecommerce.dto.request;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
