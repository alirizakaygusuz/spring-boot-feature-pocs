package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponse {

	private String accessToken;
	
	private String refreshToken;
}