package com.alirizakaygusuz.jwt_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JwtPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtPocApplication.class, args);
	}

}
