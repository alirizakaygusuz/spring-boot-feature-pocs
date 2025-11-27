package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.model;

import java.time.Instant;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.audit.AuditBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
public class RefreshToken extends AuditBase {
	
	@Column(nullable = false, unique = true, length = 512)
	private String token;

	@Column(nullable = false)
	private Instant expiryDate;

	@Builder.Default
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean revoked = false;

	@Column
	private Instant revokedAt;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "user_agent")
	private String userAgent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_user_id", nullable = false)
	private AuthUser authUser;

	public boolean isExpired() {
		return expiryDate.isBefore(Instant.now());
	}
	
	public void revoke(Instant now) {
	    this.revoked = true;
	    this.revokedAt = now;
	}

}
