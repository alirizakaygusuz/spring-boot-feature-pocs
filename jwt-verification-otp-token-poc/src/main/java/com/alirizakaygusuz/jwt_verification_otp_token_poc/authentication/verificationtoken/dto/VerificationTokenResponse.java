package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationTokenResponse {
	private String message;
    private String email;
    private String purpose;
    private Instant expiryDateTime;
}
