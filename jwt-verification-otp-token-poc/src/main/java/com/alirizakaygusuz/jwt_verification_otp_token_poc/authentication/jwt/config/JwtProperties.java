package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
public class JwtProperties {

	private String secretKey;
	private Access access;

	@Getter
	@Setter
	public static class Access {
		private long ttlMinutes;

		
	}

}
