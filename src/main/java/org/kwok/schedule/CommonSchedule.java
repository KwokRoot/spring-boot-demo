package org.kwok.schedule;

import cn.hutool.core.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description: 配置定时调度任务。
 * @author: kwok
 * @date: 2022/7/15
 */
@Component
@EnableScheduling
public class CommonSchedule {

    private static final Logger logger = LoggerFactory.getLogger(CommonSchedule.class);

    @Scheduled(cron = "0 */1 * * * ?")
    public void heartbeatLog(){
        logger.info(">>> {} heartbeat: active", DateUtil.now());
    }

}
