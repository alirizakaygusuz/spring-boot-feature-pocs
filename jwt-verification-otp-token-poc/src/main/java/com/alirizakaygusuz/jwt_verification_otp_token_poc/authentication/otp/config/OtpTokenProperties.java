package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "otp")
@Getter 
@Setter
public class OtpTokenProperties {

	private long ttlSeconds;
    private long deleteAfterDays;
    private long maxAttempts;
}
