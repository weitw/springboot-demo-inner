package com.weitw.study.sbt.config;

import com.weitw.study.sbt.utils.AESUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;
import java.util.Properties;


/**
 * 用于初始化时，将配置文件中加密的数据解密后再加载程序
 * @author weitw
 * @date 2024/5/11 16:46
 */
public class DecryptEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Properties props = new Properties();  // 临时存储需要替换的配置
        // 假设加密密码前缀为 "ENC("，后缀为 ")"
        MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource instanceof EnumerablePropertySource) {
                EnumerablePropertySource<?> enumerablePropertySource = (EnumerablePropertySource<?>) propertySource;
                String[] propertyNames = enumerablePropertySource.getPropertyNames();
                // 遍历所有配置key:value
                for (String propertyName : propertyNames) {
                    String propertyVal = environment.getProperty(propertyName);
                    // 根据自己写的规则来解析那些配置是需要解密的
                    if (propertyVal != null && propertyVal.startsWith("ENC(") && propertyVal.endsWith(")")) {
                        // 解析得到加密的数据
                        String encryptedValue = propertyVal.substring(4, propertyVal.length() - 1);
                        // 调用自定义工具类解密
                        String decryptedValue = AESUtil.decryptEcbMode(encryptedValue);
                        // 保存需要替换的配置
                        props.put(propertyName, decryptedValue);
                    }
                }
            }
        }
        // 添加解密后的属性到环境中
        if (!props.isEmpty()) {
            PropertiesPropertySource pps = new PropertiesPropertySource("decryptedProperties", props);
            environment.getPropertySources().addFirst(pps);
        }
    }
}