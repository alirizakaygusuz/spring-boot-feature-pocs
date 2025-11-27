package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.dto;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.validation.ValidRefreshToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.util.ClientMetadataAware;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest implements ClientMetadataAware {

	@ValidRefreshToken
	@NotBlank
	private String refreshToken;
	
	private String ipAddress;
	
	private String userAgent;

}
