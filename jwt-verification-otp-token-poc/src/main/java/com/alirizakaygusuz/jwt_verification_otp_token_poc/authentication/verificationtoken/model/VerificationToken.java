package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.model;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenBase;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.mail.MailTokenPayload;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "verification_tokens")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(force = true)
public class VerificationToken extends TokenBase implements MailTokenPayload {
	
	@Column(name = "verification_token", nullable = false, unique = true)
	private String verificationToken;

	@OneToOne(targetEntity = AuthUser.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_user_id", nullable = false, unique = true)
	private AuthUser authUser;

	@Override
	public String getTokenValue() {
		return this.verificationToken;
	}

	@Override
	public String getRecipientEmail() {
		return this.getAuthUser().getEmail();
	}
}