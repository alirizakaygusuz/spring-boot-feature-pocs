package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.service.VerificationTokenService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.controller.BaseResponseController;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.response.StandardApiResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping(value = "/api/v1/auth/verification-token" , 
				produces = "application/json"
)
@RequiredArgsConstructor
public class VerificationTokenController extends BaseResponseController {
	
	private final VerificationTokenService verificationTokenService;
	
	@GetMapping("/verify")
	public ResponseEntity<StandardApiResponse<String>> verify(@RequestParam("token") String token){
		verificationTokenService.verifyVerificationToken(token);
		
		return ok("Account successfully verified.");
	}

}