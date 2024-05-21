package com.weitw.study.sbt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(value = {"com.weitw.study.sbt.mapper"})
public class SpringDemoApp {
    public static void main(String[] args) {
//        SpringApplication.run(SpringDemoApp.class, args);

        SpringApplication springApplication = new SpringApplication(SpringDemoApp.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
