package com.weitw.study.sbt.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@ConditionalOnProperty(prefix = "rpa", name = "open", havingValue = "true")
@ConditionalOnProperty(name = "test-controller", havingValue = "true", matchIfMissing = false)
public class TestConditionalOnPropertyController {
    @GetMapping("/rpa/test")
    public String test(String value) {
        System.out.println("test-value:" + value);
        return "返回：" + value;
    }
}
