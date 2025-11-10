package com.alirizakaygusuz.jwt_permission_based_poc.common.mapper;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
		componentModel = "spring" , 
	    unmappedTargetPolicy = ReportingPolicy.IGNORE,
	    builder = @Builder(disableBuilder = true)

)
public interface CentralMapperConfig {

}
