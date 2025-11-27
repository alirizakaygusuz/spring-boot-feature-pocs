package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto;

import java.util.Set;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.relation.userrole.dto.UserRoleResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.dto.SoftDeletableAuditBaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserResponse extends SoftDeletableAuditBaseDto {

	private String username;

	private String email;

	private boolean isEnabled ;

	private Set<UserRoleResponse> userRoles;
	
}

