package com.weitw.study.sbt.schedules;

import com.weitw.study.sbt.mapper.TJobInfoMapper;
import com.weitw.study.sbt.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class DynamicScheduled2TaskService {

    @Autowired
    private TJobInfoMapper jobInfoMapper;

    @Autowired
    private TaskScheduler taskScheduler;

    private ScheduledFuture<?> futureTask;

    private String currentCronExpression;

    @PostConstruct
    public void init() {
        String cronExpression = jobInfoMapper.findCronByUuid("dynamic2"); // 从数据库获取cron表达式
        scheduleTask(cronExpression);
    }

    public void scheduleTask(String cronExpression) {
        this.currentCronExpression = cronExpression;
        if (futureTask != null) {
            futureTask.cancel(true); // 取消之前的任务
        }
        futureTask = taskScheduler.schedule(this::executeTask, new CronTrigger(cronExpression)); // 使用新的cron表达式调度任务
    }

    public void executeTask() {
        // 这里是你的任务执行逻辑
        log.info("任务2执行, cron={}, now={}", currentCronExpression, DateUtils.normalDateFormat(new Date()));
    }

    // 可以定期调用此方法来检查数据库中的cron表达式是否有变化
    @Scheduled(fixedDelay = 30000) // 例如，每分钟检查一次
    public void checkAndUpdateCronExpression() {
        String newCronExpression = jobInfoMapper.findCronByUuid("dynamic2");
        if (!newCronExpression.equals(currentCronExpression)) {
            scheduleTask(newCronExpression); // 如果cron表达式有变化，则重新调度任务
        }
    }
}