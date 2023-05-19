package com.example.user.config.threadPool;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * desc: 线程属性类
 *
 * @author: xwx
 * @mail: 12306@126.com
 * @create 2018-05-08 9:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "thredpool")
public class ThreadPoolProperties {

    @ApiModelProperty("cachedThreadPool线程池核心线程数量")
    private Integer cacheCorePoolSize;

    @ApiModelProperty("cachedThreadPool线程池 最大线程数")
    private Integer cacheMaximumPoolSize;

    @ApiModelProperty("cachedThreadPool线程池空闲线程超时回收时间")
    private Long cacheKeepAliveTime;

    @ApiModelProperty("fixedThreadPool线程池核心线程及最大线程数量")
    private Integer fixedPoolSize;

    @ApiModelProperty("fixedThreadPool线程池空闲线程超时回收时间")
    private Long fixedKeepAliveTime;

    @ApiModelProperty("fixedThreadPool线程池队列长度")
    private Integer fixedQueueSize;

    @ApiModelProperty("核心线程数")
    private Integer freeCoreTreadPoolSize;

    @ApiModelProperty("最大线程数")
    private Integer freeMaximumPoolSize;

    @ApiModelProperty("空闲线程回收时间")
    private Long freeKeepAliveTime;
}