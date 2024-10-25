package com.weitw.study.sbt;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class RocketMqApp {
    public static void main(String[] args) {
//        SpringApplication.run(SpringDemoApp.class, args);

        SpringApplication springApplication = new SpringApplication(RocketMqApp.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
