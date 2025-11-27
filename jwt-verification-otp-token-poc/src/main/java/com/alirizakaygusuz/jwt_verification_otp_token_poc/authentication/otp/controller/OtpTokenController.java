package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.dto.OtpTokenVerifyRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.service.OtpTokenService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.controller.BaseResponseController;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.response.StandardApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/otp-token")
@RequiredArgsConstructor
public class OtpTokenController extends BaseResponseController {
	private final OtpTokenService otpTokenService;
	
	
	@PostMapping("/verify")
	public ResponseEntity<StandardApiResponse<LoginResponse>> verifyOtpTokenCode(@RequestBody @Valid OtpTokenVerifyRequest otpTokenVerifyRequest){
		
		return ok(otpTokenService.verifyOtpTokenCode(otpTokenVerifyRequest));
	}
}
