package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.core.enumerated.Status;
import io.luankuhlmann.ecommerce.core.producer.Producer;
import io.luankuhlmann.ecommerce.dto.response.OrderCreatedResponse;
import io.luankuhlmann.ecommerce.mapper.OrderMapper;
import io.luankuhlmann.ecommerce.model.Order;
import io.luankuhlmann.ecommerce.model.OrderItem;
import io.luankuhlmann.ecommerce.model.Product;
import io.luankuhlmann.ecommerce.repository.OrderRepository;
import io.luankuhlmann.ecommerce.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final ProductService productService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final Producer producer;

    public OrderCreatedResponse createOrder(Map<UUID, Integer> productQuantities) {
        Order order = new Order();
        Set<OrderItem> items = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<UUID, Integer> entry : productQuantities.entrySet()) {
            UUID productId = entry.getKey();
            int quantity = entry.getValue();

            Product product = productService.findById(productId);

            OrderCreatedResponse orderValidate = getOrderCreatedResponse(product, quantity, order);
            if (orderValidate != null) return orderValidate;

            OrderItem item = buildOrderItem(order, product, quantity);

            items.add(item);
            total = total.add(item.getPrice());
        }

        order.setItems(items);
        order.setTotal(total);
        order.setUserId(TokenUtil.extractUserIdFromToken());

        Order savedOrder = orderRepository.save(order);

        producer.sendOrderCreated("Pedido CRIADO - ID: " + savedOrder.getId() + " | Total: " + total);

        return OrderMapper.toOrderCreatedResponse(savedOrder);
    }

    private static OrderItem buildOrderItem(Order order, Product product, int quantity) {
        return OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .price(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .build();
    }

    private OrderCreatedResponse getOrderCreatedResponse(Product product, int quantity, Order order) {
        if (product.getStock() < quantity) {
            order.setStatus(Status.CANCELED);
            orderRepository.save(order);
            producer.sendOrderCreated("Pedido CANCELADO - Estoque insuficiente para produto " + product.getName());
            return OrderMapper.toOrderCreatedResponse(order);
        }
        return null;
    }
}
