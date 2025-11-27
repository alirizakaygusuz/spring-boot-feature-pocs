package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.dto.TokenEmailContent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenEmailMessageResolver {
	
	private final MessageSource messageSource;
	
	
	public TokenEmailContent resolve(TokenPurpose purpose , Locale locale , Integer ttlMinutes) {
		 String prefix = purpose.getI18nPrefix();
		 
		   String subject = getRequired(prefix +".title",locale);
	       String description = getRequired(prefix + ".description", locale);
	       String buttonLabel = getOptional(prefix + ".button", locale);
	       String urlPath = getOptional(prefix + ".url", locale);


	        String expires = null;
	        if (ttlMinutes != null) {
	            expires = getOptional(prefix + ".expires", locale, ttlMinutes);
	        }
	        
	        return new TokenEmailContent(subject, description, buttonLabel, urlPath, expires);


	}


	private String getRequired(String key, Locale locale, Object... args) {
        return messageSource.getMessage(key, args, locale);
    }


	private String getOptional(String key, Locale locale, Object... args) {
        try {
            String value = messageSource.getMessage(key, args, locale);
            return StringUtils.hasText(value) ? value : null;
        } catch (Exception e) {
            return null;
        }
    }
	

}
