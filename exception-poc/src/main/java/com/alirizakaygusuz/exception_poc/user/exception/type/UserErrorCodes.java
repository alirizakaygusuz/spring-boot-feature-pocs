package com.alirizakaygusuz.exception_poc.user.exception.type;

public final class UserErrorCodes {
   private UserErrorCodes() {
	   
   }
   
   public static final String USER_USER_NOT_FOUND =  "1001";
   public static final String USER_USER_ALREADY_EXISTS =  "1002";
   public static final String USER_EMAIL_ALREADY_EXISTS =  "1003";
   public static final String USER_INVALID_EMAIL = "1004";
   public static final String USER_REQUIRED_FIELD = "1005";
   public static final String USER_INVALID_PASSWORD = "1006";
}
