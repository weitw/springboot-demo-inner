package com.weitw.study.sbt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;

    // getters and setters
}
