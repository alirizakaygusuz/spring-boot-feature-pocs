package com.alirizakaygusuz.exception_poc.common.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.alirizakaygusuz.exception_poc.common.responnse.ErrorResponse;
import com.alirizakaygusuz.exception_poc.common.util.MessageUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
	
	private final MessageSource messageSource;
	
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse<String>> handleUserNotFoundException(BaseException ex ,WebRequest request){
	    String localizedMessage = MessageUtils.getMessage(messageSource, ex.getI18nKey(), ex.getArgs());

	    ErrorResponse<String> response = ErrorResponse.of(
	    				request,
	    				ex.getCode(),
	    				ex.getI18nKey(),
	    				localizedMessage
	    				);

        return ResponseEntity.status(ex.getStatus()).body(response);
	}
	

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse<String>> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
		
	    Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors()
	            .stream()
	            .collect(Collectors.toMap(FieldError::getField, DefaultMessageSourceResolvable::getDefaultMessage));

	    ErrorResponse<String> response = ErrorResponse.of(request,
	    		"Validation failed",fieldErrors);
	    
	    
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
