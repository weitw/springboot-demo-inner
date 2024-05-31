package com.weitw.study.sbt.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * 北明数据库连接配置
 * @author weitw
 * @date 2024/5/21 11:13
 */

@Configuration
@ConfigurationProperties(prefix = "custom-jdbc-datasource")
@Slf4j
@Data
public class CustomJdbcDataSourceConfig {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Bean(name = "beiMingDataSource")
    public DataSource beiMingDataSource() {
        log.info("加载北明数据库");
        //用druid要 new DruidDataSource() 实现类，Spring Boot 默认是不注入druid这些属性值的，需要自己绑定
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setInitialSize(1);
        dataSource.setMaxActive(5);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(TimeUnit.MILLISECONDS.toMillis(30000));
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setConnectionErrorRetryAttempts(0);
        dataSource.setBreakAfterAcquireFailure(true);
        dataSource.setTimeBetweenEvictionRunsMillis(TimeUnit.MILLISECONDS.toMillis(800));
        dataSource.setMinEvictableIdleTimeMillis(TimeUnit.MINUTES.toMillis(5));
        return dataSource;
    }

    @Bean("bmJdbcTemplate")
    public JdbcTemplate bmJdbcTemplate() {
        return new JdbcTemplate(beiMingDataSource());
    }

}