package com.alirizakaygusuz.jwt_poc.auth.authuser.service;

import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.LoginRequest;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.LoginResponse;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterRequest;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterResponse;

public interface AuthService {
	
	public RegisterResponse register(RegisterRequest request);
	
	public LoginResponse login(LoginRequest request);

}
