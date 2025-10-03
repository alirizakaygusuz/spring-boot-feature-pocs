package com.alirizakaygusuz.jwt_poc.common.response;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.context.request.WebRequest;

import com.alirizakaygusuz.jwt_poc.common.util.HostUtils;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse<T> {

	private Integer code;

	private String i18nKey;

	private String path;

	private LocalDateTime timestamp;

	private String hostName;

	private T message;

	private Map<String, String> fieldErrors;

	private ErrorResponse(Integer code, String i18nKey, String path, LocalDateTime timestamp, String hostName, T message,
			Map<String, String> fieldErrors) {
		this.code = code;
		this.i18nKey = i18nKey;
		this.path = path;
		this.timestamp = timestamp;
		this.hostName = hostName;
		this.message = message;
		this.fieldErrors = fieldErrors;
	}

	//Normal Error
	public static <T> ErrorResponse<T> of(WebRequest request, Integer code, String i18nKey, T message) {
		return new ErrorResponse<>(code, i18nKey, extractPath(request), LocalDateTime.now(), HostUtils.getHostName(),
				message, null);
	}

	// Validation Error with FieldMaps
	public static <T> ErrorResponse<T> of(WebRequest request, T message, Map<String, String> fieldErrors) {
		return new ErrorResponse<>(null, null, extractPath(request), LocalDateTime.now(), HostUtils.getHostName(),
				message, fieldErrors);
	}

	// Simple Error
	public static <T> ErrorResponse<T> of(WebRequest request, T message) {
		return new ErrorResponse<>(null, null, extractPath(request), LocalDateTime.now(), HostUtils.getHostName(),
				message, null);
	}

	private static String extractPath(WebRequest request) {
		return request.getDescription(false).replace("uri=", "");
	}

}
