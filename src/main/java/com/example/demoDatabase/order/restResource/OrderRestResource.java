package com.example.demoDatabase.order.restResource;

import com.example.demoDatabase.common.util.ResponseUtil;
import com.example.demoDatabase.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderRestResource {
    OrderService orderService;

    public OrderRestResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public Object save(@RequestParam String username) {
        return ResponseUtil.get(orderService.save(username), HttpStatus.OK);
    }
}
