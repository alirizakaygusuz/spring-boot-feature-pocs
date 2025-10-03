package com.alirizakaygusuz.jwt_poc.auth.authuser.mapper;

import org.mapstruct.Mapper;

import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.LoginResponse;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterRequest;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterResponse;
import com.alirizakaygusuz.jwt_poc.auth.authuser.model.AuthUser;

@Mapper(componentModel = "spring")
public interface AuthMapper {
	
	AuthUser toEntity(RegisterRequest request);
	
	RegisterResponse toRegisterResponse(AuthUser authUser);

	LoginResponse toLoginResponse(AuthUser authUser, String accessToken, long expiresIn);

   
}
