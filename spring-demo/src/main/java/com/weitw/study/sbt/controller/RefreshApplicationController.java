package com.weitw.study.sbt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@Slf4j
@RestController
public class RefreshApplicationController {

    @Autowired
    private Environment environment;

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @GetMapping("/findByKey")
    public String findByKey(String value) {
        log.info("{}：{}", value, environment.getProperty(value));
        log.info("{}：{}", value, configurableEnvironment.getProperty(value));
        return configurableEnvironment.getProperty(value);
    }

}
