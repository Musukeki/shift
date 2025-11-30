package com.example.p01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class P01Application {

	public static void main(String[] args) {
		SpringApplication.run(P01Application.class, args);
	}


}
