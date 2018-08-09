package com.taylor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @author taylor
 */
@Component
@Slf4j
public class ScheduledTasks {
    /**
     * 每分钟刷新推荐数据
     */
    @Scheduled(cron = "0 */5 * * * *")
    public void updateRecmdData() {

    }

    /**
     * 每30秒刷新股架数据
     */
    @Scheduled(cron = "*/30 * * * * *")
    public void updateShelfData() {

    }

    /**
     * 尾盘推荐股票
     **/
    @Scheduled(cron = "0 0 22 * * *")
    public void fetchBigYinData() {

    }
}