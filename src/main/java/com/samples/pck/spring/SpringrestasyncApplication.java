package com.samples.pck.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAsync
@ComponentScan("com.samples.pck")
@EnableWebMvc
public class SpringrestasyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestasyncApplication.class, args);
	}
	
}
