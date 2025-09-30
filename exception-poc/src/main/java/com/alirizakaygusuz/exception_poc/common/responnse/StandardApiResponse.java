package com.alirizakaygusuz.exception_poc.common.responnse;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"status","payload","timestamp"})
public class StandardApiResponse<T> {
	
	private int status;
	
	private T payload;
	
	private LocalDateTime timestamp;
	
	
	private StandardApiResponse(HttpStatus status, T payload) {
		this.status = status.value();
		this.payload = payload;
		this.timestamp = LocalDateTime.now();
	}
	
	//200
	public static <T> StandardApiResponse<T> ok(T payload){
		return new StandardApiResponse<>(HttpStatus.OK  , payload);
	}
	
	//201
    public static <T> StandardApiResponse<T> created(T payload) {
        return new StandardApiResponse<>(HttpStatus.CREATED, payload);
    }

    //204
    public static <T> StandardApiResponse<T> noContent() {
        return new StandardApiResponse<>(HttpStatus.NO_CONTENT, null);
    }

    //400
    public static <T> StandardApiResponse<T> badRequest(T errorPayload) {
        return new StandardApiResponse<>(HttpStatus.BAD_REQUEST, errorPayload);
    }

    //401
    public static <T> StandardApiResponse<T> unauthorized(T errorPayload) {
        return new StandardApiResponse<>(HttpStatus.UNAUTHORIZED, errorPayload);
    }

    //403
    public static <T> StandardApiResponse<T> forbidden(T errorPayload) {
        return new StandardApiResponse<>(HttpStatus.FORBIDDEN, errorPayload);
    }

    //404
    public static <T> StandardApiResponse<T> notFound(T errorPayload) {
        return new StandardApiResponse<>(HttpStatus.NOT_FOUND, errorPayload);
    }

    //409
    public static <T> StandardApiResponse<T> conflict(T errorPayload) {
        return new StandardApiResponse<>(HttpStatus.CONFLICT, errorPayload);
    }

    //422
    public static <T> StandardApiResponse<T> unprocessableEntity(T errorPayload) {
        return new StandardApiResponse<>(HttpStatus.UNPROCESSABLE_ENTITY, errorPayload);
    }

    //500
    public static <T> StandardApiResponse<T> error(T errorPayload) {
        return new StandardApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, errorPayload);
    }

}
