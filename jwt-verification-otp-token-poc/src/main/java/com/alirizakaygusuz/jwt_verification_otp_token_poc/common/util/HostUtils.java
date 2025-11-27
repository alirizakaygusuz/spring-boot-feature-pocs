package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public final class HostUtils {

	private HostUtils() {
       
    }

    public static String getHostName() {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
