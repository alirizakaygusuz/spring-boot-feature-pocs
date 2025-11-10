package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserUpdateRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.LoginResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_permission_based_poc.common.mapper.CentralMapperConfig;

@Mapper(config = CentralMapperConfig.class)

public interface AuthMapper {

	LoginResponse toLoginResponse(AuthUser authUser, String accessToken, long accessTokenExpiresIn, String refreshToken,
			long refreshTokenExpiresIn);


	

//	@Mapping(target = "permissions" , ignore =  true )
//	@Mapping(target = "roles" , ignore =  true )
	AuthUserResponse toAuthUserResponse(AuthUser authUser);

	AuthUser toAuthUser(AuthUserUpdateRequest authUserUpdateRequest);
	
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateAuthUser(AuthUserUpdateRequest updateRequest, @MappingTarget AuthUser authUser);

}
