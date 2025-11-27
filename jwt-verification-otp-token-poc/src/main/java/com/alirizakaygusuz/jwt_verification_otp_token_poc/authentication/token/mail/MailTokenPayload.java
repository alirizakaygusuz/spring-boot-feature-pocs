package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.mail;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;

public interface MailTokenPayload {

	String getTokenValue();
	
	String getRecipientEmail();
	
	TokenPurpose getTokenPurpose();
}
