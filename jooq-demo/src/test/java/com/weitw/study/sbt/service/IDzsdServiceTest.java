package com.weitw.study.sbt.service;

import cn.hutool.core.date.DateUtil;
import com.weitw.study.sbt.db.tables.pojos.WebPushDzsd;
import com.weitw.study.sbt.service.dao.PushDzsdDao;
import com.weitw.study.sbt.service.impl.DzsdServiceImpl;
import com.weitw.study.sbt.utils.DateUtils;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestConfiguration
public class IDzsdServiceTest extends TestCase {
    @Resource
    private DzsdServiceImpl dzsdService;

    @Test
    public void findDzsdList() {
        Date date = DateUtils.string2Date("2023-01-01 01:01:01");
        List<WebPushDzsd> list = dzsdService.findDzsdList(date);
        for (WebPushDzsd webPushDzsd : list) {
            System.out.println(webPushDzsd);
        }
    }
}