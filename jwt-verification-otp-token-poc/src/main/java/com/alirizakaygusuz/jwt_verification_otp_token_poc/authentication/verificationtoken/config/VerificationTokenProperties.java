package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "verification")
@Getter 
@Setter
public class VerificationTokenProperties {
	private long ttlMinutes;
    private long deleteAfterDays;


}
