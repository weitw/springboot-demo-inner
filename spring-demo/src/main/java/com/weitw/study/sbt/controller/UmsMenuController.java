package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.config.MyRandomConfig;
import com.weitw.study.sbt.domain.UmsMenu;
import com.weitw.study.sbt.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ums")
public class UmsMenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    @GetMapping("/findById")
    public UmsMenu test(Long id) {
        System.out.println("id:" + id);
        return umsMenuService.getById(id);
    }
}
