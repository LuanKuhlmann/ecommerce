package io.luankuhlmann.ecommerce.controller;

import io.luankuhlmann.ecommerce.dto.request.ProductRequest;
import io.luankuhlmann.ecommerce.dto.response.ProductCreatedResponse;
import io.luankuhlmann.ecommerce.mapper.ProductMapper;
import io.luankuhlmann.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductCreatedResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        ProductCreatedResponse response = productService.createProduct(productRequest);
        return ResponseEntity.created(URI.create("/product/")).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductCreatedResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCreatedResponse> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(ProductMapper.toProductCreatedResponse(productService.getProductById(id)));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProductCreatedResponse> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
