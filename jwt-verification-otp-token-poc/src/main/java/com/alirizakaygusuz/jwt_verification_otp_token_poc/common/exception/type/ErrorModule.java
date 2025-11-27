package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type;

public enum ErrorModule {
	
	AUTHENTICATION(1000,1199),
	JWT(2000, 2999),
    REFRESH_TOKEN(3000, 3999),
    USER(4000, 4999),
    ADMIN(5000, 5999),
    ROLE(6000, 6999),
    PERMISSION(7000, 7999),
	VERIFICATION_TOKEN(8000, 8999),
    OTP_TOKEN(9000, 9999),
    EMAIL(10000, 10999);

	
	private final int start; 
	private final int end;
	
	private ErrorModule(int start , int end ) {
		this.start = start;
		this.end =end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}
	
	public boolean contains(int code) {
		return code >= start && code <= end;
	}
	
}
