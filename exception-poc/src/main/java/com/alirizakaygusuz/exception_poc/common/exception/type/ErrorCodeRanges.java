package com.alirizakaygusuz.exception_poc.common.exception.type;


/**
 * Global error code allocation table
 * 
 * This class defines the reserve ranges for each module
 * 
 * Table:
 * - 1000-1999:User module errors
 */
public final class ErrorCodeRanges {
	
	private ErrorCodeRanges() {
		
	}

	//----- USER (1000-1999) -----
	public static final String USER_RANGE_START = "1000";
	public static final String USER_RANGE_END = "1999";
}
