package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
public class JpaAuditingConfig {
	 @Bean
     AuditorAware<String> auditorAware() {
        return new SecurityAuditorAware();
    }
}
