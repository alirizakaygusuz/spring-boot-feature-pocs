package com.alirizakaygusuz.jwt_poc.common.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public final class MessageUtils {
	private MessageUtils() {}

    public static String getMessage(MessageSource messageSource, String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    public static String getMessage(MessageSource messageSource, String key, Locale locale, Object... args) {
        return messageSource.getMessage(key, args, locale);
    }
}
