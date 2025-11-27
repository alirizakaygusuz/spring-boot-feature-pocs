package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core;

import java.time.Instant;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.audit.AuditBase;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor(force = true)
public abstract class TokenBase extends AuditBase{

	@Enumerated(EnumType.STRING)
	private TokenPurpose tokenPurpose;

	@Enumerated(EnumType.STRING)
	private TokenStatus tokenStatus;

	@Column(name = "expiry_date_time", nullable = false)
	private Instant expiryDateTime;
	
	
}
