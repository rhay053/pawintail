package com.pawintail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PawintailApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawintailApplication.class, args);
	}

}
