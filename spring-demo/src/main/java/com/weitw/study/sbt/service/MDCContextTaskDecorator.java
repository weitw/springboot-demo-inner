package com.weitw.study.sbt.service;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

public class MDCContextTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        // 获取当前线程中的 requestId
        String currentRequestId = MDC.get("requestId");
        MDC.getCopyOfContextMap();

        // 返回一个包装后的 Runnable
        return () -> {
            try {
                // 在子线程中设置 requestId
                if (currentRequestId != null) {
                    MDC.put("requestId", currentRequestId);
                }
                // 执行原始任务
                runnable.run();
            } finally {
                // 清除子线程中的 requestId
                MDC.remove("requestId");
            }
        };
    }
}
