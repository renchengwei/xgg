package com.xgg.jsf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 * @author renchengwei
 * @date 2019-11-23
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.xgg")
public class JsfServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsfServerApplication.class, args);
	}

}
