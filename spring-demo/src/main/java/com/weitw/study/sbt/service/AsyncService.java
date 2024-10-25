package com.weitw.study.sbt.service;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class AsyncService {

    @Autowired
    @Qualifier("loggerMDCExecutor")
    private Executor threadPoolTaskExecutor;

    @Async("loggerMDCExecutor")
    public void printRequestId() {
        String currentRequestId = MDC.get("requestId");
        log.info("1-currentRequestId:{}", currentRequestId);
    }

    public void printRequestId2() {
        threadPoolTaskExecutor.execute(() -> {
            String currentRequestId = MDC.get("requestId");
            log.info("2-currentRequestId:{}", currentRequestId);
        });
    }
}
