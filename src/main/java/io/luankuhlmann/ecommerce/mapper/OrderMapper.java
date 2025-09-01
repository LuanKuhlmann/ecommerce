package io.luankuhlmann.ecommerce.mapper;

import io.luankuhlmann.ecommerce.dto.response.OrderCreatedResponse;
import io.luankuhlmann.ecommerce.dto.response.OrderItemResponse;
import io.luankuhlmann.ecommerce.dto.response.ProductCreatedResponse;
import io.luankuhlmann.ecommerce.model.Order;
import io.luankuhlmann.ecommerce.model.OrderItem;
import io.luankuhlmann.ecommerce.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderCreatedResponse toOrderCreatedResponse(Order order) {
        return new OrderCreatedResponse(
                order.getId().toString(),
                order.getStatus().toString(),
                order.getTotal(),
                order.getCreatedAt(),
                order.getItems().stream()
                        .map(OrderMapper::toOrderItemResponse)
                        .collect(Collectors.toList())
        );
    }

    private static OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
                orderItem.getId().toString(),
                orderItem.getProduct().getName(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }

    public static List<OrderCreatedResponse> toListOrderCreatedReponse(List<Order> orderList) {
        return orderList.stream()
                .map(OrderMapper::toOrderCreatedResponse
                ).collect(Collectors.toList());
    }
}
