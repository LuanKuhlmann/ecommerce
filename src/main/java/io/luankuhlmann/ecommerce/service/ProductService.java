package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.dto.request.ProductRequest;
import io.luankuhlmann.ecommerce.dto.response.ProductCreatedResponse;
import io.luankuhlmann.ecommerce.mapper.ProductMapper;
import io.luankuhlmann.ecommerce.model.Product;
import io.luankuhlmann.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

    public List<ProductCreatedResponse> getAllProducts() {
        return ProductMapper.toListProductCreatedReponse(productRepository.findAll());
    }

    public ProductCreatedResponse updateProduct(UUID id, ProductRequest updatedProduct) {
        Product product = getProductById(id);
        product.setName(updatedProduct.name());
        product.setDescription(updatedProduct.description());
        product.setPrice(updatedProduct.price());
        product.setCategory(updatedProduct.category());
        product.setStock(updatedProduct.stock());
        return ProductMapper.toProductCreatedResponse(product);
    }

    public void deleteProduct(UUID id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public void updateProductStock(Product product, Integer quantity) {
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + productId));
    }
}
