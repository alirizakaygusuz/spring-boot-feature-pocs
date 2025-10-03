package com.alirizakaygusuz.jwt_poc.auth.jwt.exception.type;

public final class JwtErrorCodes {

	private JwtErrorCodes() {
		
	}
	
	public static final int JWT_EXPIRED_TOKEN_CODE= 2001;
	public static final int JWT_MALFORMED_TOKEN_CODE= 2002;
	public static final int JWT_UNSUPPORTED_TOKEN_CODE= 2003;
	public static final int JWT_GENERAL_TOKEN_EXCEPTION_CODE= 2004;
}
