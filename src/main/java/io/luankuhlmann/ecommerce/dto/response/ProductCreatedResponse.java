package io.luankuhlmann.ecommerce.dto.response;

import java.math.BigDecimal;

public record ProductCreatedResponse(
        String uuid,
        String name,
        String description,
        BigDecimal price,
        String category,
        Integer stock
) {
}
