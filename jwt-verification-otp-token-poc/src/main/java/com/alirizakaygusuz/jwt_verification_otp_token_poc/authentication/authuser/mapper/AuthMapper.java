package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.mapper;

import org.mapstruct.Mapper;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.register.RegisterResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.config.CentralMapperConfig;

@Mapper(config = CentralMapperConfig.class)
public interface AuthMapper {
	
	RegisterResponse toRegisterResponse(AuthUser authUser);
}
