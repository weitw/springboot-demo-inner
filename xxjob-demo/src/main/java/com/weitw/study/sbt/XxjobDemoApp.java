package com.weitw.study.sbt;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ServletComponentScan
public class XxjobDemoApp {
    public static void main(String[] args) {
//        SpringApplication.run(SpringDemoApp.class, args);

        SpringApplication springApplication = new SpringApplication(XxjobDemoApp.class);
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }
}
