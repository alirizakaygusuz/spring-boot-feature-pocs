package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class SoftDeletableAuditBaseDto extends AuditBaseDto{
	
	private boolean deleted;

	private String deletedBy;

	private Instant deletedAt;

	
}
