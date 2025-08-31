package io.luankuhlmann.ecommerce.controller;

import io.luankuhlmann.ecommerce.dto.request.ProductRequest;
import io.luankuhlmann.ecommerce.dto.response.ProductCreatedResponse;
import io.luankuhlmann.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
