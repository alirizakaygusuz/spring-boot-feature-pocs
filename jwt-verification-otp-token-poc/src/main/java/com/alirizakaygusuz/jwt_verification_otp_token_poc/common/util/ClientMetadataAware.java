package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.util;

import jakarta.servlet.http.HttpServletRequest;

public interface ClientMetadataAware {
	void setIpAddress(String ipAddress);
	void setUserAgent(String userAgent);
	
	default void injectClientMetaData(HttpServletRequest request) {
		this.setIpAddress(IpUtils.getClientIp(request));
		this.setUserAgent(IpUtils.getUserAgent(request));
	}
}