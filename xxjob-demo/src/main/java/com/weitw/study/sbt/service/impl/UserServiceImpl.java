package com.weitw.study.sbt.service.impl;

import com.weitw.study.sbt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public void demoJob() {
        log.info(">>>>>>>>>> 开始处理实际业务逻辑 >>>>>>>>>>>>>>>");
        log.info(">>>>>>>>>> 完成处理实际业务逻辑 >>>>>>>>>>>>>>>");
    }
}

