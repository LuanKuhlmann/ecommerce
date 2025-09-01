package io.luankuhlmann.ecommerce.mapper;

import io.luankuhlmann.ecommerce.dto.request.ProductRequest;
import io.luankuhlmann.ecommerce.dto.response.ProductCreatedResponse;
import io.luankuhlmann.ecommerce.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .category(productRequest.category())
                .stock(productRequest.stock())
                .build();
    }

    public static ProductCreatedResponse toProductCreatedResponse(Product product){
        return new ProductCreatedResponse(
                product.getId().toString(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().name(),
                product.getStock()
        );
    }

    public static List<ProductCreatedResponse> toListProductCreatedReponse(List<Product> productList) {
        return productList.stream()
                .map(ProductMapper::toProductCreatedResponse
                ).collect(Collectors.toList());
    }
}
