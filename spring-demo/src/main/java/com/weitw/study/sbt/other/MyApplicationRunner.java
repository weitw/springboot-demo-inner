package com.weitw.study.sbt.other;

import com.weitw.study.sbt.config.MyRandomConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements CommandLineRunner {
    
    private final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

    @Autowired
    private Environment environment;

    @Autowired
    private MyRandomConfig myRandomConfig;

    @Override
    public void run(String... args) throws Exception {
        logger.info("myRandomConfig:{}", myRandomConfig);
        testApplication();
    }

    public void testApplication() {
        logger.info("打印配置：" + environment.getProperty("appfile"));
        logger.info("打印配置：" + environment.getProperty("rpa.open"));
        logger.info("打印配置：" + environment.getProperty("spring.datasource.password"));
        logger.info("打印配置：" + environment.getProperty("ES_HOME"));
        logger.info("打印配置：" + environment.getProperty("windir"));
        logger.info("打印配置：" + environment.getProperty("my-random.secret"));
        logger.info("打印配置：" + environment.getProperty("my-random.number-in-range"));

    }
}
