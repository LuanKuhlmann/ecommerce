package io.luankuhlmann.ecommerce.dto.request;

import java.util.UUID;

public record OrdemItemRequest(
        UUID productId,
        Integer quantity
) {
}
