package com.pay;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableDubbo(scanBasePackages  = "com.pay.modules")

public class PayApplication {


	private final static Logger LOGGER = LoggerFactory.getLogger(PayApplication.class);

	public static void main(String[] args){
		SpringApplication.run(PayApplication.class, args);
		LOGGER.info("支付项目启动，官网：https://pay.cloudbed.vip");
	}
}
