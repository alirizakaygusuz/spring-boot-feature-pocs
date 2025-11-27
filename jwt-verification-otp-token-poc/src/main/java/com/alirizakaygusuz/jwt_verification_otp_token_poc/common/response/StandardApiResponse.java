package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.response;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonPropertyOrder({ "code", "status","reason","message", "data", "timestamp" })
public class StandardApiResponse<T> {

	private final int code;
	private final String status;
	private final String reason;
	private final String message;
	private final T data;

	private final Instant timestamp;
	
	
	private StandardApiResponse(HttpStatus status , String message , T data){
		 	this.code = status.value();
		    this.status = status.name();
		    this.reason = status.getReasonPhrase();
		    this.message = message;
		    this.data = data;
		    this.timestamp = Instant.now();
		
	}
	
	//200
	public static <T> StandardApiResponse<T> ok(T data) {
	    return new StandardApiResponse<>(HttpStatus.OK, "success", data);
	}

	//201
	public static <T> StandardApiResponse<T> created(T data) {
	    return new StandardApiResponse<>(HttpStatus.CREATED, "created", data);
	}

	//204
	public static StandardApiResponse<Void> noContent() {
	    return new StandardApiResponse<>(HttpStatus.NO_CONTENT, null, null);
	}

}