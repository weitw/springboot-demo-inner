package com.weitw.study.sbt.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "products") // 定义索引名称
public class Product {
    @Id
    private String id;
    private String name;
    private String category;
    private String description;
    private double price;
}
