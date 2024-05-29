package com.weitw.study.sbt.controller;

import cn.hutool.core.util.IdUtil;
import com.weitw.study.sbt.config.MyRandomConfig;
import com.weitw.study.sbt.service.BeanA;
import com.weitw.study.sbt.service.BeanB;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

//    @Autowired
    private MyRandomConfig myRandomConfig;

    private BeanA beanA;

    @Autowired
    private BeanB beanB;

    // 构造器注入
    public IndexController(MyRandomConfig myRandomConfig, BeanA beanA) {
        this.myRandomConfig = myRandomConfig;
        this.beanA = beanA;
    }

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

    // 测试循环依赖
    @GetMapping("/test3")
    public String test3(String name, HttpServletResponse response) {
        response.setHeader("uuid", IdUtil.fastSimpleUUID());
        if ("A".equalsIgnoreCase(name)) {
            return beanA.print();
        }
        return beanB.print();
    }

}
