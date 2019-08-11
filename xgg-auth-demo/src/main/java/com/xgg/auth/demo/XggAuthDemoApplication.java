package com.xgg.auth.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xgg")
public class XggAuthDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XggAuthDemoApplication.class, args);
	}

}
