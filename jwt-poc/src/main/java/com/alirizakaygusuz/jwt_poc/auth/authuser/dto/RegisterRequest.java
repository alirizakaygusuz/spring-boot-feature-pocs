package com.alirizakaygusuz.jwt_poc.auth.authuser.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest {

	@NotBlank(message = "Username must not be blank")
	private String username;

	@NotBlank(message = "Email must not be blank")
	@Email(message = "Please provide a valid email address")
	private String email;

	@NotBlank(message = "First name must not be blank")
	private String firstName;

	@NotBlank(message = "Last name must not be blank")
	private String lastName;

	@NotBlank(message = "Password must not be blank")
	private String password;

}
