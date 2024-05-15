package com.weitw.study.sbt.domain;

import lombok.Data;

@Data
public class MyDataSource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Integer poolSize;
}
