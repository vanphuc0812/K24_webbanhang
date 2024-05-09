package com.example.demoDatabase.product.restResourse;

import com.example.demoDatabase.common.util.ResponseUtil;
import com.example.demoDatabase.product.dto.ProductDTO;
import com.example.demoDatabase.product.model.Product;
import com.example.demoDatabase.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/product")
public class ProductRestResourse {
    private final ProductService productService;

    public ProductRestResourse(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public Object findAll() {
        return ResponseUtil.get(productService.findAll(ProductDTO.class), HttpStatus.OK);
    }


    @PostMapping("/save")
    public Object save(@RequestBody ProductDTO dto) {
        return ResponseUtil.get(productService.save(dto, Product.class, ProductDTO.class), HttpStatus.OK);
    }
}
