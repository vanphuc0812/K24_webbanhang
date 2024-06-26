package com.example.demoDatabase.product.restResourse;

import com.example.demoDatabase.common.util.ResponseUtil;
import com.example.demoDatabase.product.dto.ProductDTO;
import com.example.demoDatabase.product.dto.ProductDTOForSave;
import com.example.demoDatabase.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/findByID")
    public Object findByID(@RequestParam UUID id) {
        return ResponseUtil.get(productService.findById(id, ProductDTO.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object save(@RequestBody ProductDTOForSave dto) {
        return ResponseUtil.get(productService.save(dto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByID")
    public Object deleteByID(@RequestParam UUID id) {
        productService.delete(id);
        return ResponseUtil.get("Delete successfully", HttpStatus.OK);
    }
}
