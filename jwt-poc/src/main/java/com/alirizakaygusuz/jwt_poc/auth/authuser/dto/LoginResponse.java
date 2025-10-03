package com.alirizakaygusuz.jwt_poc.auth.authuser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	private String username;
    private String email;
    private String accessToken;
    private String tokenType = "Bearer"; 
    private long expiresIn; 

}
