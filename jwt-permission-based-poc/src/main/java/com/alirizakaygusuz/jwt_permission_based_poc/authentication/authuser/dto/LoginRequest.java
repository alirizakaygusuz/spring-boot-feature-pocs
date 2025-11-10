package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto;

import com.alirizakaygusuz.jwt_permission_based_poc.common.util.ClientMetadataAware;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest implements ClientMetadataAware {
	
	@NotBlank(message = "Username or Email must not be blank")
	private String emailOrUsername;
	
	@NotBlank(message = "Password must mot be blank")
	private String password;

	private String ipAddress;
    private String userAgent;
	@Override
	public void setIpAddress(String ipAdress) {
		this.ipAddress = ipAdress;		
	}
	@Override
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;		
	}

}
