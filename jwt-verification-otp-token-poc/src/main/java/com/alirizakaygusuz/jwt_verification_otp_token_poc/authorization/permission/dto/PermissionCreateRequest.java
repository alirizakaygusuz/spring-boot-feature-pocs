package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.dto;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.type.PermissionCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionCreateRequest {

	private String permissionKey;

	private String description;

	private PermissionCategory category;
}
