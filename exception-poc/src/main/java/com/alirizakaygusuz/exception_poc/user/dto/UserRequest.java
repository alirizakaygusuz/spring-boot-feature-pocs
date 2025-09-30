package com.alirizakaygusuz.exception_poc.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotBlank
	private String username;

	@NotBlank
	@Email
	@Size(max = 120)
	private String email;

	@NotBlank
	@Size(min = 6, max = 100)
	private String password;
	
	@NotBlank
	@Size(max = 50)
	private String firstName;

	@NotBlank
	@Size(max = 50)
	private String lastName;

}
