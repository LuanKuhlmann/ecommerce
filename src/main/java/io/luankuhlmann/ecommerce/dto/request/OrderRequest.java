package io.luankuhlmann.ecommerce.dto.request;

import java.util.List;

public record OrderRequest(
        List<OrdemItemRequest> items
) {
}
