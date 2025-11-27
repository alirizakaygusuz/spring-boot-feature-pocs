package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.register.RegisterRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.register.RegisterResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.service.AuthService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.controller.BaseResponseController;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.response.StandardApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthUserController extends BaseResponseController {

	private final AuthService authUserService;

	
	@PostMapping("/register")
	public ResponseEntity<StandardApiResponse<RegisterResponse>> register(
			@RequestBody @Valid RegisterRequest registerRequest, HttpServletRequest httpServletRequest) {

		registerRequest.injectClientMetaData(httpServletRequest);

		return created(authUserService.register(registerRequest));

	}
	
	@PostMapping("/login")
	public ResponseEntity<StandardApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest,
			HttpServletRequest httpServletRequest) {

		loginRequest.injectClientMetaData(httpServletRequest);

		return ok(authUserService.login(loginRequest));
	}

	
	

}
