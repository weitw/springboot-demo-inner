package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.domain.Result;
import com.weitw.study.sbt.service.IDzsdService;
import com.weitw.study.sbt.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IDzsdService dzsdService;

    @GetMapping("/findList")
    public Result findList() {
        Date date = DateUtils.string2Date("2023-01-01 01:01:01");
        return Result.ok(dzsdService.findDzsdList(date));
    }
}
