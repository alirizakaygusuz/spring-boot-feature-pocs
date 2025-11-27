package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.register;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.dto.SoftDeletableAuditBaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse extends SoftDeletableAuditBaseDto {
	
	private String username;
	
	private String email;
	
	private String message;
}
