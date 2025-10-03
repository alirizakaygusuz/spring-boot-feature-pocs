package com.alirizakaygusuz.jwt_poc.auth.authuser.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
	
	@NotBlank(message = "Username or Email must not be blank")
	private String emailOrUsername;
	
	@NotBlank(message = "Password must not be blank")
	private String password;

}
