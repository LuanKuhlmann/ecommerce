package io.luankuhlmann.ecommerce.dto.request;

import java.util.UUID;

public record OrderItemRequest(
        UUID productId,
        Integer quantity
) {
}
