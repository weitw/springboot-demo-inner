package com.weitw.study.sbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@ServletComponentScan
//@MapperScan和dao层添加@Mapper注解意思一样
@MapperScan(basePackages = "com.weitw.study.sbt.dao")
public class MybatisPlusDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDemoApp.class, args);
    }
}
