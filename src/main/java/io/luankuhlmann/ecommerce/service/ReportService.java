package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public List<Map<String, Object>> getTop5UsersByPurchase() {
        return orderRepository.findTop5UsersByPurchase();
    }

    public List<Map<String, Object>> getAverageTicketPerUser() {
        return orderRepository.findAverageTicketPerUser();
    }

    public Map<String, Object> getTotalRevenueThisMonth() {
        return orderRepository.findTotalRevenueThisMonth();
    }
}
