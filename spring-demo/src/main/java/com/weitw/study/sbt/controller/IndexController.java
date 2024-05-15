package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.config.MyRandomConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private MyRandomConfig myRandomConfig;

    @GetMapping("/test1")
    public String test1(String value) {
        log.info("value={}", value);
        System.out.println(value);
        System.out.println(MDC.get("requestId"));
        return value;
    }

    @GetMapping("/test2")
    public MyRandomConfig test2() {
        return myRandomConfig;
    }

}
