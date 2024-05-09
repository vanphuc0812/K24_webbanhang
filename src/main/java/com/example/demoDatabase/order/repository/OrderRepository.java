package com.example.demoDatabase.order.repository;

import com.example.demoDatabase.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order save(Order order);

    Optional<Order> findById(UUID id);
}
