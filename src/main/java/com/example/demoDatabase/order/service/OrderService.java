package com.example.demoDatabase.order.service;

import com.example.demoDatabase.common.service.GenericService;
import com.example.demoDatabase.order.dto.OrderDTO;
import com.example.demoDatabase.order.dto.OrderDTOForSave;
import com.example.demoDatabase.order.model.Order;

import java.util.List;
import java.util.Map;
import java.util.UUID;


public interface OrderService extends GenericService<Order, OrderDTO, UUID> {

    List<OrderDTO> findByUsername(String username);

    OrderDTO save(OrderDTOForSave orderDTOForSave);

    OrderDTO addProducts(UUID orderID, Map<UUID, Integer> productQuantitys);

    OrderDTO removeProducts(UUID orderID, Map<UUID, Integer> productQuantitys);
}
