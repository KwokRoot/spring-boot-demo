package org.kwok.schedule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 自定义调度器执行的线程池。
 * @author: Kwok
 * @date: 2025/2/22
 */
@Configuration
public class CustomSchedulingConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(taskExecutor());

    }

    private Executor taskExecutor() {

        // 自定义线程池参数
        int corePoolSize = 5; // 核心线程数
        int maximumPoolSize = 10; // 最大线程数
        long keepAliveTime = 60L; // 线程空闲时间
        TimeUnit unit = TimeUnit.SECONDS;  // 时间单位

        // 自定义线程工厂，用于创建线程
        ThreadFactory threadFactory = new ThreadFactory() {

            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "schedu-task-t" + threadNumber.getAndIncrement());
                if (t.isDaemon()) {
                    t.setDaemon(false);
                }
                if (t.getPriority() != Thread.NORM_PRIORITY) {
                    t.setPriority(Thread.NORM_PRIORITY);
                }
                return t;
            }
        };

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(corePoolSize, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy()) {};
        scheduledThreadPoolExecutor.setMaximumPoolSize(maximumPoolSize);
        scheduledThreadPoolExecutor.setKeepAliveTime(keepAliveTime, unit);

        return scheduledThreadPoolExecutor;
    }

}
