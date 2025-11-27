package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.util.ClientMetadataAware;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.validation.ValidEmailOrUsername;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements ClientMetadataAware {

	@NotBlank
	@ValidEmailOrUsername
	String emailOrUsername;

	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Password must contain at least 6 characters, including uppercase, lowercase, number, and special character")
	String password;
	
	private String ipAddress;

	private String userAgent;

}
