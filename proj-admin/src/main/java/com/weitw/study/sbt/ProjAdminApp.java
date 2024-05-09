package com.weitw.study.sbt;

import org.dromara.x.file.storage.spring.EnableFileStorage;
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
@EnableFileStorage
@EnableAspectJAutoProxy(exposeProxy=true)
public class ProjAdminApp {
    public static void main(String[] args) {
        SpringApplication.run(ProjAdminApp.class, args);
    }
}
