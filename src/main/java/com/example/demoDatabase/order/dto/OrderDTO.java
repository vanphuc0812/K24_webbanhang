package com.example.demoDatabase.order.dto;

import com.example.demoDatabase.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private UUID id;
    private UserDTO user;
    private Set<OrderProductDTO> products;
    private BigDecimal totalPrice;
}
