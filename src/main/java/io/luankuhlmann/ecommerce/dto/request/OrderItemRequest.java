package io.luankuhlmann.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemRequest(
        @NotNull(message = "Informe um produto")
        UUID productId,

        @NotNull(message = "Informe uma quantidade")
        Integer quantity
) {
}
