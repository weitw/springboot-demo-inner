package com.weitw.study.sbt.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;  
import org.springframework.data.elasticsearch.annotations.Field;  
import org.springframework.data.elasticsearch.annotations.FieldType;  

@Document(indexName = "my-index", type = "my-type")
public class MyEntity {
    @Id
    private String id;
    @Field(type = FieldType.Text, fielddata = true)
    private String name;
    // 其他字段和getter/setter方法...


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}