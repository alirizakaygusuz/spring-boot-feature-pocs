package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpTokenVerifyRequest {
	
	@NotBlank(message = "OTP code cannot be blank")
	private String otpCode;
	
	@NotBlank(message = "OTP verification token cannot be blank")
	private String otpToken;

}
