package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Profile({"dev","prod"})
public class SchedulingConfig {
}
