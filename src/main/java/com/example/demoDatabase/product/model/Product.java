package com.example.demoDatabase.product.model;

import com.example.demoDatabase.common.model.BaseEntity;
import com.example.demoDatabase.order.model.OrderEntity;
import com.example.demoDatabase.order.model.OrderProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(mappedBy = OrderEntity.OrderProduct.PRODUCT_MAPPED_ORDERPRODUCT,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<OrderProduct> orderProducts = new HashSet<>();


}
