package io.luankuhlmann.ecommerce.dto.request;

import io.luankuhlmann.ecommerce.core.enumerated.ProductCategory;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        ProductCategory category,
        Integer stock

) {
}
