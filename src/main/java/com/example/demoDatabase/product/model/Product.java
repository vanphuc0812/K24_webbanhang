package com.example.demoDatabase.product.model;

import com.example.demoDatabase.common.model.BaseEntity;
import com.example.demoDatabase.order.model.Order;
import com.example.demoDatabase.order.model.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ProductEntity.TABLE_NAME)
public class Product extends BaseEntity {
    @Column(name = ProductEntity.NAME)
    private String name;
    @Column(name = ProductEntity.DESCRIPTION)
    private String description;
    @Column(name = ProductEntity.PRICE)
    private Double price;

    @ManyToMany(mappedBy = OrderEntity.OrderProduct.ORDER_MAPPED_PRODUCT)
    private Set<Order> orders = new HashSet<>();
}
