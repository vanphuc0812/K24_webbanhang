package com.example.demoDatabase.order.repository;

import com.example.demoDatabase.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("""
                SELECT o FROM Order o
                WHERE o.user.username = :username
            """)
    List<Order> findAllByUsername(String username);
}
