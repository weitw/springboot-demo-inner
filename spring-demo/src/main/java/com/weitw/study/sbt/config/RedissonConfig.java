//package com.weitw.study.sbt.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.util.StringUtils;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * redisson 配置，下面是单节点配置：
// * 官方wiki地址：https://github.com/redisson/redisson/wiki/2.-%E9%85%8D%E7%BD%AE%E6%96%B9%E6%B3%95#26-%E5%8D%95redis%E8%8A%82%E7%82%B9%E6%A8%A1%E5%BC%8F
// *
// */
//@Configuration
//public class RedissonConfig {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private String port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Bean
//    public RedissonClient redissonClient(){
//        Config config = new Config();
//        //单节点
//        config.useSingleServer().setAddress("redis://" + host + ":" + port);
//       if(StringUtils.hasText(password)) {
//    	   config.useSingleServer().setPassword(password);
//       }else {
//    	   config.useSingleServer().setPassword(null);
//       }
//        // 此项务必设置,为redisson解决之前bug的timeout问题关键
//        config.useSingleServer().setPingConnectionInterval(1000);// 心跳检测，定时与redis连接，可以防止一段时间过后，与redis的连接断开
//        //添加主从配置
////        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
//
//        // 集群模式配置 setScanInterval()扫描间隔时间，单位是毫秒,  //可以用"rediss://"来启用SSL连接
////        config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001").addNodeAddress("redis://127.0.0.1:7002");
//        return Redisson.create(config);
//    }
//
//    @Bean
//    public JedisPoolConfig getRedisConfig(){
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(800);
//        config.setMaxIdle(200);
//        config.setMinIdle(50);
//        config.setMaxWaitMillis(10000);
//        return config;
//    }
//
//    @Bean
//    public JedisConnectionFactory getConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(host);
//        factory.setPort(Integer.valueOf(port));
//        factory.setPassword(password);
//        JedisPoolConfig config = getRedisConfig();
//        factory.setPoolConfig(config);
//        return factory;
//    }
//
//    @Bean
//    public RedisTemplate<?, ?> getRedisTemplate() {
//        JedisConnectionFactory factory = getConnectionFactory();
//        RedisTemplate<?, ?> redisTemplate = new StringRedisTemplate(factory);
//        return redisTemplate;
//    }
//
//    @Bean(name = "intRedisTemplate")
//    public RedisTemplate<String, Integer> intRedisTemplate() {
//        JedisConnectionFactory factory = getConnectionFactory();
//
//        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//
//        // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        return redisTemplate;
//    }
//
//
//}