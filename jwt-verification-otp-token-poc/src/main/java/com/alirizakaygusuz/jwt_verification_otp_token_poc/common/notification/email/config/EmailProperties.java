package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Getter 
@Setter
public class EmailProperties {

	private String username;

}
