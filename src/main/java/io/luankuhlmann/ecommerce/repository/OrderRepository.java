package io.luankuhlmann.ecommerce.repository;

import io.luankuhlmann.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM Order o WHERE o.userId = :userId")
    List<Order> findByUserId(@Param("userId") Long userId);

    @Query(value = """
        SELECT o.user_id AS userId, SUM(o.total) AS totalSpent
        FROM orders o
        GROUP BY o.user_id
        ORDER BY totalSpent DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Map<String, Object>> findTop5UsersByPurchase();

    @Query(value = """
    SELECT o.user_id AS userId, AVG(o.total) AS averageTicket
    FROM orders o
    GROUP BY o.user_id
    """, nativeQuery = true)
    List<Map<String, Object>> findAverageTicketPerUser();

    @Query(value = """
    SELECT DATE_FORMAT(o.created_at, '%Y-%m') AS month, SUM(o.total) AS totalRevenue
    FROM orders o
    WHERE YEAR(o.created_at) = YEAR(CURRENT_DATE())
      AND MONTH(o.created_at) = MONTH(CURRENT_DATE())
    GROUP BY month
    """, nativeQuery = true)
    Map<String, Object> findTotalRevenueThisMonth();

}
