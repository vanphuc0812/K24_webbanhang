package com.example.demoDatabase.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTOForSave {
    private String name;
    private String description;
    private Double price;
}
