package com.inkbound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InkboundApplication {

	public static void main(String[] args) {
		SpringApplication.run(InkboundApplication.class, args);
	}

}