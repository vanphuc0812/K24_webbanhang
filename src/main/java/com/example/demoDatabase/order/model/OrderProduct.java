package com.example.demoDatabase.order.model;

import com.example.demoDatabase.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = OrderEntity.OrderProduct.TABLE_NAME)
public class OrderProduct {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Order order;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;
    private int quantity;
}
