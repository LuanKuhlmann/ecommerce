package io.luankuhlmann.ecommerce.controller;

import io.luankuhlmann.ecommerce.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/top-users")
    public List<Map<String, Object>> getTop5UsersByPurchase() {
        return reportService.getTop5UsersByPurchase();
    }

    @GetMapping("/average-ticket")
    public List<Map<String, Object>> getAverageTicketPerUser() {
        return reportService.getAverageTicketPerUser();
    }

    @GetMapping("/revenue-month")
    public Map<String, Object> getTotalRevenueThisMonth() {
        return reportService.getTotalRevenueThisMonth();
    }
}
