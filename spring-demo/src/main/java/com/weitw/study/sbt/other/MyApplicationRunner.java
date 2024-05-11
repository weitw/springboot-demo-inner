package com.weitw.study.sbt.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        testApplication();
    }

    public void testApplication() {
        System.out.println("打印配置：" + environment.getProperty("appfile"));
        System.out.println("打印配置：" + environment.getProperty("rpa.open"));
        System.out.println("打印配置：" + environment.getProperty("spring.datasource.password"));
        System.out.println("打印配置：" + environment.getProperty("ES_HOME"));
        System.out.println("打印配置：" + environment.getProperty("windir"));
    }
}
