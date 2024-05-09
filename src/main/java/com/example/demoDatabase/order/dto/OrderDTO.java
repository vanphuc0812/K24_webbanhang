package com.example.demoDatabase.order.dto;

import com.example.demoDatabase.product.model.Product;
import com.example.demoDatabase.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID id;
    private UserDTO user;
    private Set<Product> products;
}
