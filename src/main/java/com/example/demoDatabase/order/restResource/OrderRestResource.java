package com.example.demoDatabase.order.restResource;

import com.example.demoDatabase.common.util.ResponseUtil;
import com.example.demoDatabase.order.dto.OrderDTO;
import com.example.demoDatabase.order.dto.OrderDTOForSave;
import com.example.demoDatabase.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderRestResource {
    OrderService orderService;

    public OrderRestResource(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('WBH_ADMIN')")
    public Object findAll() {
        return ResponseUtil.get(orderService.findAll(OrderDTO.class), HttpStatus.OK);
    }

    @GetMapping("/findByID")
    public Object findByID(@RequestParam UUID id) {
        return ResponseUtil.get(orderService.findById(id, OrderDTO.class), HttpStatus.OK);
    }


    @GetMapping("/findByUsername")
    @PreAuthorize("hasAuthority('WBH_USER')") // userDetail.getAuthorities().contains("WBH_USER")
    public Object findByUsername(@RequestParam String username) {
        return ResponseUtil.get(orderService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object save(@RequestBody OrderDTOForSave orderDTOForSave) {
        return ResponseUtil.get(orderService.save(orderDTOForSave), HttpStatus.OK);
    }

    @PostMapping("/{order-id}/addProducts")
    public Object addProduct(@PathVariable("order-id") UUID orderID, @RequestBody Map<UUID, Integer> productQuantitys) {
        return ResponseUtil.get(orderService.addProducts(orderID, productQuantitys), HttpStatus.OK);
    }

    @PostMapping("/{order-id}/removeProducts")
    public Object removeProduct(@PathVariable("order-id") UUID orderID, @RequestBody Map<UUID, Integer> productQuantitys) {
        return ResponseUtil.get(orderService.removeProducts(orderID, productQuantitys), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByID")
    public Object deleteByID(@RequestParam UUID id) {
        orderService.delete(id);
        return ResponseUtil.get("Detele sucessfully", HttpStatus.OK);
    }
}
