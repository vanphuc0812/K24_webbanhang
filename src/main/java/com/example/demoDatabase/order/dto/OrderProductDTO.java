package com.example.demoDatabase.order.dto;

import com.example.demoDatabase.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO {
    private ProductDTO product;
    private int quantity;
}
