package com.weitw.study.sbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@ServletComponentScan
@EnableAspectJAutoProxy(exposeProxy=true)
public class SwaggerDemoApp {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerDemoApp.class, args);
    }
}
