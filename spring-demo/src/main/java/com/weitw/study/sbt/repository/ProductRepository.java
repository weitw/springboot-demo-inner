package com.weitw.study.sbt.repository;

import com.weitw.study.sbt.domain.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    @Query("{\"match\": {\"name\": {\"query\": \"?0\"}}}")
    List<Product> findByName(String name);
}
