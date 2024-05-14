package com.example.demoDatabase.order.repository;

import com.example.demoDatabase.order.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
    @Query("SELECT op from OrderProduct op where op.order.id = :orderID AND op.product.id = :productID")
    Optional<OrderProduct> findByProductIDAndOrderID(UUID productID, UUID orderID);
}
