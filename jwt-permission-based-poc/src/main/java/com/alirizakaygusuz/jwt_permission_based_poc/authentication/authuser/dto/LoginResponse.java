package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	
	
	private String accessToken;
	private long accessTokenExpiresIn;
    private String refreshToken;
    private long refreshTokenExpiresIn;


}