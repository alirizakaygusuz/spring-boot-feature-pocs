package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "jwt.refresh")
@Getter 
@Setter
public class RefreshTokenProperties {
	
	private boolean rotationEnabled;
	
	private long ttlDays;
	
	private long cleanupDays;
	

}
