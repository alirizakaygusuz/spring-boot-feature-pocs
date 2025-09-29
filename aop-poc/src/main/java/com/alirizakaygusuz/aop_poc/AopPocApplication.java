package com.alirizakaygusuz.aop_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.alirizakaygusuz.aop_poc")
public class AopPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopPocApplication.class, args);
	}

}
