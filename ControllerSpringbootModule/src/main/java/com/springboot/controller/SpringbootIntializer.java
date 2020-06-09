package com.springboot.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springboot")
public class SpringbootIntializer {

	public static void main(String[] args) {
			SpringApplication.run(SpringbootIntializer.class, args);
	}

}
