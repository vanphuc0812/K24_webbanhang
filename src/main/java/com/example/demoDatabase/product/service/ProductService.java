package com.example.demoDatabase.product.service;

import com.example.demoDatabase.common.service.GenericService;
import com.example.demoDatabase.product.dto.ProductDTO;
import com.example.demoDatabase.product.model.Product;

import java.util.UUID;

public interface ProductService extends GenericService<Product, ProductDTO, UUID> {

}
