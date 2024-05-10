package com.weitw.study.sbt.dao;

import com.weitw.study.sbt.domain.UmsMenu;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UmsMenuDaoTest {
    @Autowired
    private UmsMenuDao umsMenuDao;

    @Test
    public void test() {
        UmsMenu umsMenu = umsMenuDao.selectById(1);
        System.out.println(umsMenu);
    }
}