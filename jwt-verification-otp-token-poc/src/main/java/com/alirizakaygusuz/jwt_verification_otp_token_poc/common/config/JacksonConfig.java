package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.config;

import java.util.TimeZone;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonConfig {
	
	@Bean
	Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> builder
				.serializationInclusion(JsonInclude.Include.NON_NULL)
				.timeZone(TimeZone.getTimeZone("UTC"))
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	}

}