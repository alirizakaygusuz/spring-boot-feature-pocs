package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core;


public enum TokenPurpose {
    EMAIL_VERIFICATION("token.email-verification", 30),
    RESET_PASSWORD("token.reset-password", 15),
    TWO_FACTOR_AUTH("token.two-factor-auth", 3);

    private final String i18nPrefix;
    private final int expiryMinutes;

    TokenPurpose(String i18nPrefix, int expiryMinutes) {
        this.i18nPrefix = i18nPrefix;
        this.expiryMinutes = expiryMinutes;
    }

    public String getI18nPrefix() {
        return i18nPrefix;
    }

    public int getExpiryMinutes() {
        return expiryMinutes;
    }
}
