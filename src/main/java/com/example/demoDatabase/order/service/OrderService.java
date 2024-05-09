package com.example.demoDatabase.order.service;

import com.example.demoDatabase.common.service.GenericService;
import com.example.demoDatabase.order.dto.OrderDTO;
import com.example.demoDatabase.order.model.Order;

import java.util.UUID;


public interface OrderService extends GenericService<Order, OrderDTO, UUID> {

    OrderDTO save(String username);
}
