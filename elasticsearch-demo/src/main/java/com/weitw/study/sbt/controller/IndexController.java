package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.domain.MyEntity;
import com.weitw.study.sbt.domain.Result;
import com.weitw.study.sbt.service.MyEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/esDemo")
public class IndexController {

    @Autowired
    private MyEntityService entityService;

    @GetMapping("/saveEntity")
    public Result saveEntity() {
        MyEntity myEntity = new MyEntity();
        myEntity.setId("1");
        myEntity.setName("张三");
        return Result.ok(entityService.saveEntity(myEntity));
    }
}
