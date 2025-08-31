package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.dto.request.ProductRequest;
import io.luankuhlmann.ecommerce.dto.response.ProductCreatedResponse;
import io.luankuhlmann.ecommerce.mapper.ProductMapper;
import io.luankuhlmann.ecommerce.model.Product;
import io.luankuhlmann.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductCreatedResponse createProduct(ProductRequest productRequest) {
        Product newProduct = ProductMapper.toEntity(productRequest);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.toProductCreatedResponse(savedProduct);
    }

    public Product findById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + productId));
    }
}
