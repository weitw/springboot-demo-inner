package com.weitw.study.sbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(value = {"com.weitw.study.sbt.generate.mapper", "com.weitw.study.sbt.dao"})
public class SpringDemoApp {
    public static void main(String[] args) {
//        SpringApplication.run(SpringDemoApp.class, args);

        SpringApplication springApplication = new SpringApplication(SpringDemoApp.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
