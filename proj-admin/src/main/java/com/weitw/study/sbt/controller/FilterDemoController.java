package com.weitw.study.sbt.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter")
@Slf4j
public class FilterDemoController {

    @GetMapping("/test")
    public String test(String name) {
        log.info("name={}", name);
        return name;
    }

}
