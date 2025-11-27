package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.response.StandardApiResponse;

public abstract class BaseResponseController {
	
	public <T> ResponseEntity<StandardApiResponse<T>> ok(T data){
		return ResponseEntity.ok(StandardApiResponse.ok(data));
	}
	
	public <T> ResponseEntity<StandardApiResponse<T>> created(T data){
		return ResponseEntity.status(HttpStatus.CREATED).body(StandardApiResponse.created(data));
	}
	
	public <T> ResponseEntity<StandardApiResponse<Void>> noContent(){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
