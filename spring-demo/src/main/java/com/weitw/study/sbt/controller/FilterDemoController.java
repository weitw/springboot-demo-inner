package com.weitw.study.sbt.controller;

import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter-demo")
public class FilterDemoController {

    @GetMapping("/test1")
    public String test1(String value) {
        System.out.println(value);
        System.out.println(MDC.get("requestId"));
        return value;
    }
}
