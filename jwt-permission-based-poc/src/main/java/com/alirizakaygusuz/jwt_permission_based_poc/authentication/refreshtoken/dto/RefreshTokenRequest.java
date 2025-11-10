package com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.dto;

import com.alirizakaygusuz.jwt_permission_based_poc.common.util.ClientMetadataAware;

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

	@NotBlank(message = "Refresh Token can not be blank")
	private String refreshToken;
	
	private String ipAddress;
	
	private String userAgent;
}
