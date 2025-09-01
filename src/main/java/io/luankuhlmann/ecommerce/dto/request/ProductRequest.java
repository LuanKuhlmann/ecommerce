package io.luankuhlmann.ecommerce.dto.request;

import io.luankuhlmann.ecommerce.core.enumerated.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Informe um nome para o produto")
        String name,
        String description,

        @NotNull(message = "Informe um valor para o produto")
        BigDecimal price,

        @NotBlank(message = "Informe uma categoria")
        ProductCategory category,

        @NotNull(message = "Informe uma quantidade em estoque")
        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        Integer stock

) {
}
