package com.weitw.study.sbt.controller;

import com.weitw.study.sbt.domain.UmsMenu;
import com.weitw.study.sbt.mapper.primary.TMenuMapper;
import com.weitw.study.sbt.mapper.secondary.CmsHelpMapper;
import com.weitw.study.sbt.service.CustomJdbcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/dataSource")
public class MultiDataSourcesController {

    @Autowired
    private TMenuMapper menuDao;

    @Autowired
    private CmsHelpMapper cmsHelpDao;

    @Autowired
    private CustomJdbcService customJdbcService;

    @GetMapping("/db1")
    public String test1() {
        return menuDao.findById(1);
    }

    @GetMapping("/db2")
    public String test2() {
        return cmsHelpDao.queryById(1);
    }

    @GetMapping("/db3")
    public void test3(Integer id) {
        customJdbcService.insertBatchTest();
    }

}
