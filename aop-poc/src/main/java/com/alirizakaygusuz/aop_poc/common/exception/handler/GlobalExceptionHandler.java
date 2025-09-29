package com.alirizakaygusuz.aop_poc.common.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.alirizakaygusuz.aop_poc.common.response.ApiErrorResponse;
import com.alirizakaygusuz.aop_poc.common.validation.exception.FieldValidationException;
import com.alirizakaygusuz.aop_poc.common.validation.exception.ParamValidationException;
import com.alirizakaygusuz.aop_poc.order.exception.InvalidOrderStatusException;
import com.alirizakaygusuz.aop_poc.order.exception.OrderAlreadyExistsException;
import com.alirizakaygusuz.aop_poc.order.exception.OrderNotFoundException;
import com.alirizakaygusuz.aop_poc.product.exception.ProductAlreadyExistsException;
import com.alirizakaygusuz.aop_poc.product.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleProductNotFound(ProductNotFoundException ex , WebRequest request){
		return buildErrorResponse(ex , HttpStatus.NOT_FOUND , "PRODUCT_NOT_FOUND" , request);
	}
	
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ResponseEntity<ApiErrorResponse> handleProductAlreadyExists(ProductAlreadyExistsException ex , WebRequest request){
		return buildErrorResponse(ex , HttpStatus.CONFLICT , "PRODUCT_ALREADY_EXISTS", request);

	}
	

	@ExceptionHandler(InvalidOrderStatusException.class)
	public ResponseEntity<ApiErrorResponse> handlePInvalidOrderStatus(InvalidOrderStatusException ex , WebRequest request){
		return buildErrorResponse(ex , HttpStatus.BAD_REQUEST , "INVALID_ORDER_STATUS", request);

	}
	
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleOrderNotFound(OrderNotFoundException ex , WebRequest request){
		return buildErrorResponse(ex , HttpStatus.NOT_FOUND , "ORDER_NOT_FOUND", request);

	}
	
	@ExceptionHandler(OrderAlreadyExistsException.class)
	public ResponseEntity<ApiErrorResponse> handleOrderAlreadyExists(ProductAlreadyExistsException ex , WebRequest request){
		return buildErrorResponse(ex , HttpStatus.CONFLICT , "ORDER_ALREADY_EXISTS", request);

	}
	
	
	@ExceptionHandler(ParamValidationException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(ParamValidationException ex , WebRequest request){
		return buildErrorResponse(ex , HttpStatus.BAD_REQUEST , "PARAM_VALIDATION_ERROR", request);

	}
	
	@ExceptionHandler(FieldValidationException.class)
	public ResponseEntity<ApiErrorResponse> handleFieldValidation(FieldValidationException ex , WebRequest request){
		return buildErrorResponse(ex , HttpStatus.BAD_REQUEST , "FIELD_VALIDATION_ERROR", request);

	}
	

	
	private ResponseEntity<ApiErrorResponse> buildErrorResponse(Exception ex , HttpStatus status ,String errorCode , WebRequest request){
		ApiErrorResponse errorReponse = ApiErrorResponse.builder()
				.timeStamp(LocalDateTime.now())
				.status(status.value())
				.error(errorCode)
				.message(ex.getMessage())
				.path(request.getDescription(false).replace("uri=" , ""))
				.build();
		
		return ResponseEntity.status(status).body(errorReponse);
	}
}
