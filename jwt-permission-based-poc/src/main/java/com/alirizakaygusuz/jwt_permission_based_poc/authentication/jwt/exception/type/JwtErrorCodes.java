package com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type;

public final class JwtErrorCodes {

    private JwtErrorCodes() {
    }

    public static final int JWT_EXPIRED_TOKEN_CODE = 2001;
    public static final int JWT_INVALID_TOKEN_SIGNATURE_CODE = 2002;
    public static final int JWT_MALFORMED_TOKEN_CODE = 2003;
    public static final int JWT_UNSUPPORTED_TOKEN_CODE = 2004;
    public static final int JWT_GENERAL_TOKEN_EXCEPTION_CODE = 2005;
}