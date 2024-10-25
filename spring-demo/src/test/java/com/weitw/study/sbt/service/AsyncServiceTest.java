package com.weitw.study.sbt.service;

import cn.hutool.core.util.IdUtil;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class AsyncServiceTest extends TestCase {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void testPrintRequestId() {
        String requestId = IdUtil.fastSimpleUUID();
        MDC.put("requestId", requestId);
        log.info("requestId-start:{}", requestId);
        asyncService.printRequestId();
        asyncService.printRequestId2();
        new Thread(() -> {
            log.info("new Thread:" + MDC.get("requestId"));
        }).start();
    }

    @Test
    public void testPrintRequestId2() {
        String requestId = IdUtil.fastSimpleUUID();
        MDC.put("requestId", requestId);
        asyncService.printRequestId2();
    }
}