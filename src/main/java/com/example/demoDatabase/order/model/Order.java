package com.example.demoDatabase.order.model;

import com.example.demoDatabase.common.model.BaseEntity;
import com.example.demoDatabase.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    @OneToMany(mappedBy = OrderEntity.OrderProduct.ORDER_MAPPED_ORDERPRODUCT
            , cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<OrderProduct> orderProducts = new HashSet<>();
    private BigDecimal totalPrice;
}
