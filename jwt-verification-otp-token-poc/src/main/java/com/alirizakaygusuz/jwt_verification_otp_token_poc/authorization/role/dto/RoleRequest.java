package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.dto;

import java.util.Set;

import lombok.Data;

@Data
public class RoleRequest {
	
	private String name;
	
	private String description;
	
	
	private Set<Long> permissionsId;

}
