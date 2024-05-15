package com.weitw.study.sbt.service;

import com.weitw.study.sbt.dao.TMenuDao;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ICmsHelpServiceTest extends TestCase {

    @Autowired
    private TMenuDao menuDao;

    @Test
    public void test1() {
        String cmsHelp = menuDao.findById(1);
        log.info("UmsAdmin:{}", cmsHelp);
    }

}