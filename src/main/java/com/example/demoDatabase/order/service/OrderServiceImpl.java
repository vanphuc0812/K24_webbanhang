package com.example.demoDatabase.order.service;

import com.example.demoDatabase.common.exception.WBHBussinessExeption;
import com.example.demoDatabase.common.util.WBHMapper;
import com.example.demoDatabase.order.dto.OrderDTO;
import com.example.demoDatabase.order.model.Order;
import com.example.demoDatabase.order.repository.OrderRepository;
import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    UserRepository userRepository;
    WBHMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, WBHMapper mapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDTO save(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            Order newOrder = new Order();
            newOrder.setUser(userOptional.get());
            orderRepository.save(newOrder);
            return mapper.map(newOrder, OrderDTO.class);
        } else throw new WBHBussinessExeption("User not found");
    }

    @Override
    public JpaRepository<Order, UUID> getRepository() {
        return orderRepository;
    }

    @Override
    public WBHMapper getMapper() {
        return mapper;
    }

}
