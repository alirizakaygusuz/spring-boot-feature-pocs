package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.template;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.mail.MailTokenPayload;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.dto.TokenEmailContent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ThymeleafTokenEmailTemplateEngine implements TokenEmailTemplateEngine {

	private final TemplateEngine templateEngine;

	@Override
	public String render(TokenPurpose purpose, MailTokenPayload payload, TokenEmailContent messages,
			String actionUrl) {

		Context context = new Context();
		context.setVariable("payload", payload);
		context.setVariable("messages", messages);
		context.setVariable("actionUrl", actionUrl);

		String templateName = switch (purpose) {
				case TWO_FACTOR_AUTH -> "mail/token-two-factor";
				default -> "mail/token-default";
			};

		return templateEngine.process(templateName, context);

	}

}