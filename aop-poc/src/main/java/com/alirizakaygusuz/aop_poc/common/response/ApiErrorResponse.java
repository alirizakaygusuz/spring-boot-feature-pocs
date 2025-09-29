package com.alirizakaygusuz.aop_poc.common.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrorResponse {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
	private LocalDateTime timeStamp;
	private int status;
	private String error;
	private String message;
	private String path;
}
