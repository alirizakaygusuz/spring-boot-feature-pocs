package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.model;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenBase;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.mail.MailTokenPayload;

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
@Table(name = "otp_tokens")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
public class OtpToken extends TokenBase implements MailTokenPayload {
	
	@Column(nullable = false, length = 6)
	private String otpCode;

	@Column(nullable = false, unique = true)
	private String otpToken;

	@Builder.Default
	@Column(nullable = false, columnDefinition = "INT DEFAULT 0")
	private int attemptCount = 0;
	
	@ManyToOne(targetEntity = AuthUser.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_user_id", nullable = false)
	private AuthUser authUser;


	private String ipAddress;

	private String userAgent;

	@Override
	public String getTokenValue() {
		return this.otpCode;
		
	}

	@Override
	public String getRecipientEmail() {
		return this.getAuthUser().getEmail();
	}

}
