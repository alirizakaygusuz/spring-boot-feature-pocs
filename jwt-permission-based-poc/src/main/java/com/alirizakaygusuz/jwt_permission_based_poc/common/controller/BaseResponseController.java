package com.alirizakaygusuz.jwt_permission_based_poc.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alirizakaygusuz.jwt_permission_based_poc.common.response.StandardApiResponse;

public abstract class BaseResponseController {

	//200
    public <T> ResponseEntity<StandardApiResponse<T>> ok(T payload) {
        return ResponseEntity.ok(StandardApiResponse.ok(payload));
    }

    //201
    public <T> ResponseEntity<StandardApiResponse<T>> created(T payload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(StandardApiResponse.created(payload));
    }

    //204
    public <T> ResponseEntity<StandardApiResponse<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //400
    public <T> ResponseEntity<StandardApiResponse<T>> badRequest(T errorPayload) {
        return ResponseEntity.badRequest().body(StandardApiResponse.badRequest(errorPayload));
    }

    //401
    public <T> ResponseEntity<StandardApiResponse<T>> unauthorized(T errorPayload) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(StandardApiResponse.unauthorized(errorPayload));
    }

    //403
    public <T> ResponseEntity<StandardApiResponse<T>> forbidden(T errorPayload) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(StandardApiResponse.forbidden(errorPayload));
    }

    //404
    public <T> ResponseEntity<StandardApiResponse<T>> notFound(T errorPayload) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardApiResponse.notFound(errorPayload));
    }

    //409
    public <T> ResponseEntity<StandardApiResponse<T>> conflict(T errorPayload) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(StandardApiResponse.conflict(errorPayload));
    }
    
    //422
    public <T> ResponseEntity<StandardApiResponse<T>> unprocessableEntity(T errorPayload){
    		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(StandardApiResponse.unprocessableEntity(errorPayload));
    }

    //500
    public <T> ResponseEntity<StandardApiResponse<T>> error(T errorPayload) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StandardApiResponse.error(errorPayload));
    }
}