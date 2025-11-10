package com.alirizakaygusuz.jwt_permission_based_poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JwtPermissionBasedPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtPermissionBasedPocApplication.class, args);
	}

}
