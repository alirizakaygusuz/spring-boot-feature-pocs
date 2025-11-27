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
public abstract class AuditBaseDto {

	private Long id;

	private Instant createdAt;

	private Instant updatedAt;

	private String createdBy;

	private String updatedBy;
}
