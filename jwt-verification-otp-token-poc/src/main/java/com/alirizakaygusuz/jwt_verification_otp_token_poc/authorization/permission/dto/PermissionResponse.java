package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.dto;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.type.PermissionCategory;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.dto.SoftDeletableAuditBaseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponse extends SoftDeletableAuditBaseDto{
    private String permissionKey;
    private String description;
    private PermissionCategory category;

}
