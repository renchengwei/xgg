package com.xgg.bsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 * @author renchengwei
 * @date 2019-11-23
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.xgg.auth.service"})
public class BsfServerApplication {
	public static void main(String[] args) {
		System.setProperty("nacos.logging.config", "classpath:log4j2-spring.xml");
		SpringApplication.run(BsfServerApplication.class, args);
	}
}
