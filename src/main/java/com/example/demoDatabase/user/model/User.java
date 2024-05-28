package com.example.demoDatabase.user.model;

import com.example.demoDatabase.common.model.BaseEntity;
import com.example.demoDatabase.order.model.Order;
import com.example.demoDatabase.order.model.OrderEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Getter
@Setter
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class User extends BaseEntity {
    @Column(name = UserEntity.USERNAME)
    private String username;
    @Column(name = UserEntity.PASSWORD)
    private String password;
    @Column(name = UserEntity.AGE)
    private int age;
    @Column(name = UserEntity.GENDER)
    private String gender;
    @Column(name = UserEntity.ROLES)
    private String roles;
    private String avatar;

    @OneToMany(mappedBy = OrderEntity.OrderUser.ORDER_MAPPED_USER)
    private Set<Order> orders = new HashSet<>();
}

