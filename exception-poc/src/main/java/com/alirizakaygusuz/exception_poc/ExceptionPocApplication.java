package com.alirizakaygusuz.exception_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.alirizakaygusuz.exception_poc")
public class ExceptionPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionPocApplication.class, args);
	}
	

}
