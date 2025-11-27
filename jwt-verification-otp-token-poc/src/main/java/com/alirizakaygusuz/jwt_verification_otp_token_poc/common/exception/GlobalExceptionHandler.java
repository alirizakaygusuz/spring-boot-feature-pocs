package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.response.ErrorResponse;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	
	private final MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse<String>> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
		
	    Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors()
	            .stream()
	            .collect(Collectors.toMap(
	            		FieldError::getField,
	            		DefaultMessageSourceResolvable::getDefaultMessage,
	            		(msg1 ,msg2) -> msg1 //  duplicate -> choose first validation message
	            		));

	    ErrorResponse<String> response = ErrorResponse.validation(
	    														request,
	    													    "Validation failed",
	    														fieldErrors
	    														);
	    
	    
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
