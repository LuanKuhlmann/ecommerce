package io.luankuhlmann.ecommerce.controller;

import io.luankuhlmann.ecommerce.dto.request.OrderItemRequest;
import io.luankuhlmann.ecommerce.dto.request.OrderRequest;
import io.luankuhlmann.ecommerce.dto.response.OrderCreatedResponse;
import io.luankuhlmann.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @PostMapping("/new")
    public ResponseEntity<OrderCreatedResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        Map<UUID, Integer> productQuantities = orderRequest.items().stream()
                .collect(Collectors.toMap(OrderItemRequest::productId, OrderItemRequest::quantity));

        OrderCreatedResponse response = orderService.createOrder(productQuantities);
        return ResponseEntity.created(URI.create("/order/")).body(response);
    }
}
