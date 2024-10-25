package com.weitw.study.sbt.config;

import com.weitw.study.sbt.service.MDCContextTaskDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "loggerMDCExecutor")
    public Executor loggerMDCExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 设置核心线程数
        executor.setMaxPoolSize(20);  // 设置最大线程数
        executor.setQueueCapacity(100); // 设置队列容量
        executor.setTaskDecorator(new MDCContextTaskDecorator()); // 使用自定义的 TaskDecorator
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        return executor;
    }
}
