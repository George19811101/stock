package com.example.user.config.threadPool;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * desc: 线程池
 *
 * @author: xwx
 * @mail: 12306.com
 * @create 2018-05-08 9:11
 */
@Component
public class ThreadPoolConfig {

    @Autowired
    private ThreadPoolProperties threadPoolProperties;

    /**
     * core是0，所有的线程都可以回收 (主要用于处理异步编排时handle捕获的异常线程任务)
     *
     * @return
     */
    @Bean
    public ThreadPoolExecutor cachedThreadPool() {

        return new ThreadPoolExecutor(threadPoolProperties.getCacheCorePoolSize(),
                threadPoolProperties.getCacheMaximumPoolSize(),
                threadPoolProperties.getCacheKeepAliveTime(),
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

    }

    /**
     * 固定大小的线程池 core = max 都不可回收
     *
     * @return
     */
    @Bean
    public ThreadPoolExecutor fixedThreadPool() {

        return new ThreadPoolExecutor(threadPoolProperties.getFixedPoolSize(),
                threadPoolProperties.getFixedPoolSize(),
                threadPoolProperties.getFixedKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(threadPoolProperties.getFixedQueueSize()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

    }

    /**
     * 自由线程
     *
     * @return
     */
    @Bean
    public ThreadPoolExecutor freeThreadPool() {

        return new ThreadPoolExecutor(threadPoolProperties.getFreeCoreTreadPoolSize(),
                threadPoolProperties.getFreeMaximumPoolSize(),
                threadPoolProperties.getFreeKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(threadPoolProperties.getFixedQueueSize()),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

    }

    /**
     * 定时任务
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {

        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.initialize();

        return threadPoolTaskScheduler;

    }

}
