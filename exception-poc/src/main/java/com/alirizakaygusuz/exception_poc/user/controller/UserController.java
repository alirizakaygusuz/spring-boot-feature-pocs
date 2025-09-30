package com.alirizakaygusuz.exception_poc.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.exception_poc.common.responnse.StandardApiResponse;
import com.alirizakaygusuz.exception_poc.user.dto.UserRequest;
import com.alirizakaygusuz.exception_poc.user.dto.UserResponse;
import com.alirizakaygusuz.exception_poc.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<StandardApiResponse<UserResponse>> createUser(@Valid @RequestBody UserRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(StandardApiResponse.created(userService.createUser(request)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StandardApiResponse<UserResponse>> getUserById(@PathVariable long id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(StandardApiResponse.ok(userService.getUserById(id)));
		
	}
	
	@GetMapping
	public ResponseEntity<StandardApiResponse<List<UserResponse>>> getAllUsers(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(StandardApiResponse.ok(userService.getAllUsers()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StandardApiResponse<UserResponse>> updateUser(@PathVariable long id ,@Valid @RequestBody UserRequest request){
		return ResponseEntity.status(HttpStatus.OK)
				.body(StandardApiResponse.ok(userService.updateUser(id,request)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<StandardApiResponse<Void>> deleteUserById(@PathVariable long id){
		userService.deleteUserById(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
 	

}
