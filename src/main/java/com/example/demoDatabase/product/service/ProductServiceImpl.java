package com.example.demoDatabase.product.service;

import com.example.demoDatabase.common.util.WBHMapper;
import com.example.demoDatabase.product.model.Product;
import com.example.demoDatabase.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final WBHMapper mapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(WBHMapper mapper, ProductRepository productRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    public JpaRepository<Product, UUID> getRepository() {
        return productRepository;
    }

    @Override
    public WBHMapper getMapper() {
        return mapper;
    }
}
