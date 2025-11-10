package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AuthUserResponse {
	
	private String username;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private Set<String> roles;
	
	private Set<String> permissions;
	
	private LocalDateTime createdTime;

}
