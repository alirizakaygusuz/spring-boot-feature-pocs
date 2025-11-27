package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
	
	private boolean otpRequired;
	
	private String message;
	private String otpToken;
	
    private long otpExpiresInSeconds;
    
	
	private String username;

	private String email;

	private String accessToken;

	private String refreshToken;
	
	
	
	


	
}
