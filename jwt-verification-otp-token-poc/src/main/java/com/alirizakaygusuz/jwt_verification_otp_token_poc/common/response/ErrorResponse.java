package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.response;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.util.HostUtils;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;

@Getter
@JsonPropertyOrder({ "code", "i18nKey", "path", "timestamp", "hostName", "message", "fieldErrors" })
public class ErrorResponse<T> {

	private final int code;

	private final String i18nKey;

	private final String path;

	private final Instant timestamp;

	private final String hostName;

	private final T message;

	private final Map<String, String> fieldErrors;
	
	private ErrorResponse(int code, String i18nKey, String path, Instant timestamp, String hostName, T message,
			Map<String, String> fieldErrors) {
		this.code = code;
		this.i18nKey = i18nKey;
		this.path = path;
		this.timestamp = timestamp;
		this.hostName = hostName;
		this.message = message;
		this.fieldErrors = fieldErrors;
	}

	// Normal Error
	public static <T> ErrorResponse<T> error(WebRequest request, int code, String i18nKey, T message) {
		return new ErrorResponse<>(
							code, 
							i18nKey, 
							extractPath(request), 
							Instant.now(), 
							HostUtils.getHostName(),
							message,
							null
						);
	}

	// Validation Error with FieldMaps
	public static <T> ErrorResponse<T> validation(WebRequest request, T message, Map<String, String> fieldErrors) {
		return new ErrorResponse<>(
							HttpStatus.BAD_REQUEST.value(),
							"error_response.error",
							extractPath(request), 
							Instant.now(), 
							HostUtils.getHostName(),
							message,
							fieldErrors
						);
	}

	// Simple Error
	public static <T> ErrorResponse<T> simple(WebRequest request, T message) {
		return new ErrorResponse<>(
							HttpStatus.BAD_REQUEST.value(),
							"error_response.error", 
							extractPath(request), 
							Instant.now(),
							HostUtils.getHostName(),
							message,
							null
						);
	}
	
	public static <T> ErrorResponse<T> unauthorized(WebRequest request, T message) {
	    return new ErrorResponse<>(
	            HttpStatus.UNAUTHORIZED.value(),
	            "error_response.unauthorized",
	            extractPath(request),
	            Instant.now(),
	            HostUtils.getHostName(),
	            message,
	            null
	    );
	}

	
	public static <T> ErrorResponse<T> forbidden(WebRequest request, T message) {
	    return new ErrorResponse<>(
	            HttpStatus.FORBIDDEN.value(),
	            "error_response.forbidden",
	            extractPath(request),
	            Instant.now(),
	            HostUtils.getHostName(),
	            message,
	            null
	    );
	}


	private static String extractPath(WebRequest request) {
		return request.getDescription(false).replace("uri=", "");
	}

}
