package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.config;

import java.util.Locale;
import java.util.TimeZone;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Configuration
public class I18nConfig {
	
	
	@Bean
    MessageSource messageSource() {
       ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
       messageSource.setBasenames(
    		   "classpath:i18n/auth/error/error_auth",
    		   "classpath:i18n/auth/error/error_refresh_token",
               "classpath:i18n/auth/token/token",
               "classpath:i18n/auth/verificationtoken/error_verification_token",
               "classpath:i18n/permission/error_permission",
               "classpath:i18n/role/error_role",
               "classpath:i18n/error/response/error_response",
               "classpath:i18n/error/module/error_module",
               "classpath:i18n/user/error_user"
       		);
       messageSource.setDefaultEncoding("UTF-8");
       return messageSource;
   }

   @Bean
    LocaleResolver localeResolver() {
       CookieLocaleResolver localeResolver = new CookieLocaleResolver();
       localeResolver.setDefaultLocale(Locale.ENGLISH);
       localeResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));
       return localeResolver;
   }

}
