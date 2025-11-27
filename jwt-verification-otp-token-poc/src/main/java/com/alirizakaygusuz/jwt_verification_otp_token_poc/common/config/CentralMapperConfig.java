package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.config;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@MapperConfig(
		componentModel = "spring",
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		unmappedSourcePolicy =  ReportingPolicy.IGNORE,
		unmappedTargetPolicy = ReportingPolicy.WARN,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, // patch/update  
		builder = @Builder(disableBuilder = true)
		
)
public interface CentralMapperConfig {

}