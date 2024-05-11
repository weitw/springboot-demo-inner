package com.weitw.study.sbt.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 用于初始化时，将配置文件中加密的数据解密后再加载程序
 * @author weitw
 * @date 2024/5/11 16:46
 */
@Configuration
public class DecryptEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String ENCRYPTION_KEY = "xsy1029*#"; // 在实际应用中，这个密钥应该从更安全的地方获取

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource instanceof MapPropertySource) {
                Map<String, Object> originalMap = ((MapPropertySource) propertySource).getSource();
                Map<String, Object> decryptedMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
                    if ("spring.datasource.password".equals(entry.getKey())) {
                        decryptedMap.put(entry.getKey(), decrypt(String.valueOf(entry.getValue())));
                    } else {
                        decryptedMap.put(entry.getKey(), entry.getValue());
                    }
                }
                // 替换原有的属性源
//                propertySources.replace(propertySource.getName(), new MapPropertySource(propertySource.getName(), decryptedMap));
            }
        }
    }

    private Object decrypt(String encryptedValue) {
        // 在这里实现你的解密逻辑，以下是一个示例占位符
        // 实际上你应该使用适当的解密方法来解密该值
        return "DECRYPTED_" + encryptedValue;
    }
}