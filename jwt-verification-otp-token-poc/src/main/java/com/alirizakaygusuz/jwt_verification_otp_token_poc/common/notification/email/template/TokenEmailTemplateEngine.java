package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.template;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.mail.MailTokenPayload;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.dto.TokenEmailContent;

public interface TokenEmailTemplateEngine {

	 String render(TokenPurpose purpose,
             MailTokenPayload payload,
             TokenEmailContent messages,
             String actionUrl);
}
