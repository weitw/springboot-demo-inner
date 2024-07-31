//package com.weitw.study.sbt.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//import java.util.concurrent.TimeUnit;
//
///**
// * 北明数据库连接配置
// * @author weitw
// * @date 2024/5/21 11:13
// */
//
//@ConfigurationProperties("push-case-info-beiming.datasource")
//@Configuration
//public class MultipleDataSourceConfig {
//
//    private String url;
//
//    private String username;
//
//    private String password;
//
//    private String driverClassName;
//
//    @Value("${spring.datasource.url}")
//    private String primaryUrl;
//
//    @Value("${spring.datasource.username}")
//    private String primaryUsername;
//
//    @Value("${spring.datasource.password}")
//    private String primaryPassword;
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String primaryDriverClassName;
//
//    @Value("${spring.datasource.hikari.minimum-idle}")
//    private Integer primaryMinimumIdle;
//
//    @Value("${spring.datasource.hikari.maximum-pool-size}")
//    private Integer primaryMaxPoolSize;
//
//    @Bean(name = "primaryDataSource")
//    @Primary
//    public DataSource primaryDataSource() {
//        System.out.println("加载主数据库配置");
//        //用druid要 new DruidDataSource() 实现类，Spring Boot 默认是不注入druid这些属性值的，需要自己绑定
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(primaryUrl);
//        dataSource.setDriverClassName(primaryDriverClassName);
//        dataSource.setUsername(primaryUsername);
//        dataSource.setPassword(primaryPassword);
//
//        dataSource.setInitialSize(1);
//        dataSource.setMaxActive(primaryMaxPoolSize);
//        dataSource.setMinIdle(primaryMinimumIdle);
//        dataSource.setMaxWait(TimeUnit.MILLISECONDS.toMillis(30000));
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestOnReturn(false);
//        dataSource.setTestWhileIdle(false);
//        dataSource.setPoolPreparedStatements(false);
//        dataSource.setConnectionErrorRetryAttempts(0);
//        dataSource.setBreakAfterAcquireFailure(false);
//        dataSource.setTimeBetweenEvictionRunsMillis(TimeUnit.MILLISECONDS.toMillis(30000));
//        dataSource.setMinEvictableIdleTimeMillis(TimeUnit.MILLISECONDS.toMillis(30000));
//        return dataSource;
//    }
//
//    @Bean
//    @Primary
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(primaryDataSource());
//    }
//
//    @ConditionalOnProperty(name = "push-case-info-beiming.switch", havingValue = "true")
//    @Bean(name = "beiMingDataSource")
//    @ConfigurationProperties("push-case-info-beiming.datasource")
//    public DataSource beiMingDataSource() {
//        System.out.println("加载北明数据库配置");
//        //用druid要 new DruidDataSource() 实现类，Spring Boot 默认是不注入druid这些属性值的，需要自己绑定
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        dataSource.setUrl(url);
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//
//        dataSource.setInitialSize(1);
//        dataSource.setMaxActive(2);
//        dataSource.setMinIdle(1);
//        dataSource.setMaxWait(TimeUnit.MILLISECONDS.toMillis(30000));
//        dataSource.setValidationQuery("SELECT 1");
//        dataSource.setTestOnBorrow(false);
//        dataSource.setTestOnReturn(false);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setPoolPreparedStatements(false);
//        dataSource.setConnectionErrorRetryAttempts(0);
//        dataSource.setBreakAfterAcquireFailure(true);
//        dataSource.setTimeBetweenEvictionRunsMillis(TimeUnit.MILLISECONDS.toMillis(800));
//        dataSource.setMinEvictableIdleTimeMillis(TimeUnit.MINUTES.toMillis(5));
//        return dataSource;
//    }
//
//    @ConditionalOnProperty(name = "push-case-info-beiming.switch", havingValue = "true")
//    @Bean("bmJdbcTemplate")
//    public JdbcTemplate bmJdbcTemplate(@Qualifier("beiMingDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//}
