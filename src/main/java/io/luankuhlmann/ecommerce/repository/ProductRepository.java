package io.luankuhlmann.ecommerce.repository;

import io.luankuhlmann.ecommerce.model.Order;
import io.luankuhlmann.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
