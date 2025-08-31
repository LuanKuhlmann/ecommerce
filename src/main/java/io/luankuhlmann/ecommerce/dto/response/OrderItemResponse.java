package io.luankuhlmann.ecommerce.dto.response;

import java.math.BigDecimal;

public record OrderItemResponse(
        String uuid,
        String productName,
        Integer quantity,
        BigDecimal price
) {
}
