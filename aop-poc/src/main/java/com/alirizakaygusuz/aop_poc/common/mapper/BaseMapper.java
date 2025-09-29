package com.alirizakaygusuz.aop_poc.common.mapper;

public interface BaseMapper<E , R , C , U> {
	
    E toEntity(C request);
	
	R toResponse(E entity);
	
	void updateEntity(E entity , U updateRequest);

}
