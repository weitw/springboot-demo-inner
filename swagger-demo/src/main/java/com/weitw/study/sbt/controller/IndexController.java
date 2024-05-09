package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.domain.PmsBrandDto;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = "index-controller", value = "测试swagger")
@RestController
@RequestMapping("/index")
public class IndexController {

    @ApiOperation("简单测试")
    @GetMapping("/test1")
    public void test1() {}

    @ApiOperation("简单测试2")
    @GetMapping("/test2")
    public void test2(@RequestParam(required = false) Integer id) {
        System.out.println(id);
    }

    @ApiOperation("简单测试3")
    @GetMapping("/test3")
    public void test3(@ApiParam("id") @RequestParam(required = false) Integer id) {
        System.out.println(id);
    }

    @ApiOperation("简单测试4")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id值", paramType = "query", required = true)
    })
    @GetMapping("/test4")
    public void test4(@ApiParam("id") @RequestParam(required = false) Integer id) {
        System.out.println(id);
    }

    @ApiOperation("简单测试5")
    @GetMapping("/test5")
    public void test5(@RequestParam(required = false) PmsBrandDto pmsBrandDto) {
        System.out.println(pmsBrandDto);
    }
}
