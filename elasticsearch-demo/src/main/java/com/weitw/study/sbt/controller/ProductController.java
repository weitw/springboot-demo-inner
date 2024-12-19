package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.domain.Product;
import com.weitw.study.sbt.mapper.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // 添加商品
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // 搜索商品
    @GetMapping("/search")
    public List<Product> search(@RequestParam String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    // 按类别筛选
    @GetMapping("/category")
    public List<Product> findByCategory(@RequestParam String category) {
        return productRepository.findByCategory(category);
    }

    // 按价格区间筛选
    @GetMapping("/price")
    public List<Product> findByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productRepository.findByPriceBetween(min, max);
    }
}
