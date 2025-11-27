package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.register;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.util.ClientMetadataAware;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements ClientMetadataAware {


	@NotBlank(message = "Username cannot be blank")
	@Size(min = 3, max = 50)
	private String username;
	
	@NotBlank(message = "First name cannot be blank")
	@Size(min = 3, max = 50)
	private String firstName;
	
	@NotBlank(message = "Last name cannot be blank")
	@Size(min = 3, max = 50)
	private String lastName;
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email format is invalid")
	private String email;
	

    @NotBlank(message = "Password cannot be blank")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Password must contain at least 6 characters, including uppercase, lowercase, number, and special character")
	@Size(min = 6)
	private String password;
    
    private String ipAddress;
    
    private String userAgent;
	
}
