package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper<Entity , CreateRequest ,UpdateRequest ,Response > {

	Response toResponse(Entity entity);
	
	Entity toEntity(CreateRequest createRequest);
	
	void updateFromRequest(UpdateRequest updateRequest , @MappingTarget Entity entity);
}
