package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.domain.Product;
import com.weitw.study.sbt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        productService.deleteById(id);
    }

    @GetMapping("/search")
    public List<Product> findByName(@RequestParam String name) {
        return productService.findByName(name);
    }

    @PostMapping("/sync")
    public void syncData() {
        productService.syncData();
    }
}
