package com.alirizakaygusuz.exception_poc.user.service;

import java.util.List;

import com.alirizakaygusuz.exception_poc.user.dto.UserRequest;
import com.alirizakaygusuz.exception_poc.user.dto.UserResponse;

public interface UserService {
	
	UserResponse createUser(UserRequest request);
	
	UserResponse getUserById(long id);
	
	List<UserResponse> getAllUsers();
	
	UserResponse updateUser(long id, UserRequest request);
	
	void deleteUserById(long id);

}
