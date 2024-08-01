package com.weitw.study.sbt.service;

import com.weitw.study.sbt.domain.Product;
import com.weitw.study.sbt.mapper.primary.ProductMapper;
import com.weitw.study.sbt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

//    @Autowired
//    private RedisTemplate<String, Product> redisTemplate;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public void syncData() {
        List<Product> products = productMapper.findAll();
        productRepository.saveAll(products);
    }

//    public Optional<Product> findByIdWithRedis(String id) {
//        Product product = redisTemplate.opsForValue().get(id);
//        if (product == null) {
//            Optional<Product> optionalProduct = productRepository.findById(id);
//            if (optionalProduct.isPresent()) {
//                product = optionalProduct.get();
//                redisTemplate.opsForValue().set(id, product);
//            }
//        }
//        return Optional.ofNullable(product);
//    }
}
