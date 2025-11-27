package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TokenEmailContent {

	private String subject;
	private String description;
	private String buttonLabel;
	private String urlPath;
	private String expiresText;
}
