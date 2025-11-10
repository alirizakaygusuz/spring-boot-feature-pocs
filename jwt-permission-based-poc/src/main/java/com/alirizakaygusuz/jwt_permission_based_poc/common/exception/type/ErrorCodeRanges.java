package com.alirizakaygusuz.jwt_permission_based_poc.common.exception.type;

public final class ErrorCodeRanges {
	
	private ErrorCodeRanges() {
		
	}
	

	public static final int AUTHENTICATION_RANGE_START=1000;
	public static final int AUTHENTICATION_RANGE_END = 1999 ;
	public static final int JWT_RANGE_START=2000;
	public static final int JWT_RANGE_END = 2999 ;
	public static final int REFRESHTOKEN_RANGE_START=3000;
	public static final int REFRESHTOKEN_RANGE_END = 3999 ;
	public static final int USER_RANGE_START=4000;
	public static final int USER_RANGE_END = 4999 ;
	public static final int ADMIN_RANGE_START=5000;
	public static final int ADMIN_RANGE_END = 5999 ;
	public static final int ROLE_RANGE_START=6000;
	public static final int ROLE_RANGE_END = 6999 ;
	public static final int PERMISSION_RANGE_START=7000;
	public static final int PERMISSION_RANGE_END = 7999 ;
	

}
