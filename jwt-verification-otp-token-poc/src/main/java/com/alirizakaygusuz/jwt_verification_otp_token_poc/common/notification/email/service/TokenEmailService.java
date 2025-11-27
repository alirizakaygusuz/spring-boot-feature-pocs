package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.service;

import java.util.Locale;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.mail.MailTokenPayload;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.config.EmailProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.dto.TokenEmailContent;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.exception.EmailErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.exception.EmailException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.i18n.TokenEmailMessageResolver;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.template.TokenEmailTemplateEngine;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TokenEmailService {
	
	
	private final EmailProperties emailProperties;
	
	private final TokenEmailMessageResolver emailTokenMessageResolver;
	
	private final TokenEmailTemplateEngine tokenMailTemplateEngine;
	
	private final JavaMailSender mailSender;
	
	
	
	public void sendTokenEmail(MailTokenPayload payload, Locale locale) {

	    String toEmail = payload.getRecipientEmail();
	    TokenPurpose purpose = payload.getTokenPurpose();
	    int ttl = purpose.getExpiryMinutes();

	    TokenEmailContent messages = emailTokenMessageResolver.resolve(purpose ,locale ,ttl); 

        String actionUrl = buildActionUrl(payload.getTokenValue(), messages.getUrlPath());

        String content = tokenMailTemplateEngine.render(purpose, payload, messages, actionUrl);


        sendEmail(toEmail, messages.getSubject(), content);

	}
	
	
	private String buildActionUrl(String tokenValue, String i18nUrlPath) {

	    if (i18nUrlPath == null || i18nUrlPath.isBlank()) {
	        return null; // 2FA
	    }

	    return ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path(i18nUrlPath)
	            .queryParam("token", tokenValue)
	            .toUriString();
	}



	private void sendEmail(String toEmail, String subject, String content) {
        try {       

        	MimeMessage mimeMessage = mailSender.createMimeMessage();
		    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(toEmail);
			helper.setSubject(subject);
			helper.setFrom("no-reply" + emailProperties.getUsername());
			helper.setText(content, true);



            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException(EmailErrorType.EMAIL_SENDING_FAILED);
        }
    }
	
	

}
