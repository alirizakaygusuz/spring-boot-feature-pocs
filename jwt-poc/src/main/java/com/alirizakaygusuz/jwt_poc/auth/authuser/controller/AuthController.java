package com.alirizakaygusuz.jwt_poc.auth.authuser.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.LoginRequest;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.LoginResponse;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterRequest;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterResponse;
import com.alirizakaygusuz.jwt_poc.auth.authuser.service.AuthService;
import com.alirizakaygusuz.jwt_poc.common.response.StandardApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	
	@PostMapping("/register")
	public ResponseEntity<StandardApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(StandardApiResponse.created(authService.register(request)));
	}
	
	@PostMapping("/login")
	public ResponseEntity<StandardApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request){
		return ResponseEntity.status(HttpStatus.OK)
				.body(StandardApiResponse.ok(authService.login(request)));
	}
	
	@GetMapping
	public String privateGateWithAccessToken(){
		return "Welcome to the JWT-POC ";
	}
	
	

}
