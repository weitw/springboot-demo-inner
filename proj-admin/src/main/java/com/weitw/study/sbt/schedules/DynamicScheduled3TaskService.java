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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class DynamicScheduled3TaskService {

    @Autowired
    private TJobInfoMapper jobInfoMapper;

    @Autowired
    private TaskScheduler taskScheduler;

    private Map<String, ScheduledFuture<?>> futureTasks = new ConcurrentHashMap<>();
    private Map<String, String> cronExpressions = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        checkAndUpdateCronExpression();
    }

    // 可以定期调用此方法来检查数据库中的cron表达式是否有变化
    @Scheduled(fixedDelay = 30000) // 例如，每分钟检查一次
    public void checkAndUpdateCronExpression() {
        List<Map<String, String>> tasks = jobInfoMapper.findCrons();
        for (Map<String, String> task : tasks) {
            String uuid = task.get("job_uuid");
            String cron = task.get("job_cron");
            if (!cronExpressions.containsKey(uuid)) {
                // 新增
                scheduleTask(uuid, cron);
                continue;
            }
            if (cronExpressions.containsKey(uuid) && !cron.equals(cronExpressions.get(uuid))) {
                // 更新
                scheduleTask(uuid, cron);
            }
        }
    }

    public void scheduleTask(String uuid, String cronExpression) {
        cronExpressions.put(uuid, cronExpression);
        if (futureTasks.get(uuid) != null) {
            futureTasks.get(uuid).cancel(true);
        }
        switch (uuid) {
            case "dynamic1":
                futureTasks.put(uuid, taskScheduler.schedule(this::dynamic1Task, new CronTrigger(cronExpression))); // 使用新的cron表达式调度任务
                break;
            case "dynamic2":
                futureTasks.put(uuid, taskScheduler.schedule(this::dynamic2Task, new CronTrigger(cronExpression))); // 使用新的cron表达式调度任务
                break;
            case "dynamic3":
                futureTasks.put(uuid, taskScheduler.schedule(this::dynamic3Task, new CronTrigger(cronExpression))); // 使用新的cron表达式调度任务
                break;
        }
    }

    public void dynamic1Task() {
        // 这里是你的任务执行逻辑
        log.info("任务dynamic1Task执行, cron={}, now={}", cronExpressions.get("dynamic1"), DateUtils.normalDateFormat(new Date()));
    }
    public void dynamic2Task() {
        // 这里是你的任务执行逻辑
        log.info("任务dynamic2Task执行, cron={}, now={}", cronExpressions.get("dynamic2"), DateUtils.normalDateFormat(new Date()));
    }
    public void dynamic3Task() {
        // 这里是你的任务执行逻辑
        log.info("任务dynamic3Task执行, cron={}, now={}", cronExpressions.get("dynamic3"), DateUtils.normalDateFormat(new Date()));
    }

}