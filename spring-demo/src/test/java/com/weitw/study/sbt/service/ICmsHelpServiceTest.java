package com.weitw.study.sbt.service;

import com.weitw.study.sbt.mapper.secondary.CmsHelpMapper;
import com.weitw.study.sbt.mapper.primary.TMenuMapper;
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
    private TMenuMapper menuDao;

    @Autowired
    private CmsHelpMapper cmsHelpDao;

    @Test
    public void test1() {
        String menu = menuDao.findById(1);
        log.info("menu:{}", menu);
        log.info("cms:{}", cmsHelpDao.queryById(1));
    }

}