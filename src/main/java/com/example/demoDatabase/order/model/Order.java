package com.example.demoDatabase.order.model;

import com.example.demoDatabase.common.model.BaseEntity;
import com.example.demoDatabase.product.model.Product;
import com.example.demoDatabase.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = OrderEntity.TABLE_NAME)
public class Order extends BaseEntity {
    @ManyToOne
    @JoinTable(
            name = OrderEntity.OrderUser.TABLE_NAME,
            joinColumns = @JoinColumn(name = OrderEntity.OrderUser.ORDER_ID),
            inverseJoinColumns = @JoinColumn(name = OrderEntity.OrderUser.USER_ID)
    )
    private User user;
    @ManyToMany
    @JoinTable(
            name = OrderEntity.OrderProduct.TABLE_NAME,
            joinColumns = @JoinColumn(name = OrderEntity.OrderProduct.ORDER_ID),
            inverseJoinColumns = @JoinColumn(name = OrderEntity.OrderProduct.PRODUCT_ID)
    )
    private Set<Product> products;
}
