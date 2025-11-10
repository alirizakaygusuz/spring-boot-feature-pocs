package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.service;

import java.util.List;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserUpdateRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.LoginRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.LoginResponse;

public interface AuthUserService {

	LoginResponse login(LoginRequest request);

	AuthUserResponse getAdminByUsername(String username);

	AuthUserResponse getUserById(Long id);

	AuthUserResponse getUserByUsernameOrEmail(String usernameOrEmail);

	List<AuthUserResponse> getAllUsers();

	AuthUserResponse updateUserById(AuthUserUpdateRequest updateRequest, Long id);

	AuthUserResponse updateUserByUsernameOrEmail(AuthUserUpdateRequest updateRequest, String usernameOrEmail);

	void deleteAdminByUsername(String username);

	void deleteUserById(Long id);

	void deleteUserByUsernameOrEmail(String usernameOrEmail);
	
	AuthUserResponse getCurrentUser(String username);

	AuthUserResponse updateCurrentUser(AuthUserUpdateRequest updateRequest, String username);

	void deleteCurrentUser(String username);
}
