package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.AuthUserUpdateRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.LoginRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.dto.LoginResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.exception.AuthException;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.exception.type.AuthErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.mapper.AuthMapper;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.repository.AuthUserRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.service.AuthUserService;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.service.JwtService;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.model.RefreshToken;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService, UserDetailsService {

	private final AuthUserRepository authUserRepository;

	private final AuthMapper authMapper;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	private final RefreshTokenService refreshTokenService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
		return authUserRepository.findUserWithRolesAndPermissions(input)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with : " + input));
	}

	@Override
	@Transactional
	public LoginResponse login(LoginRequest request) {
		AuthUser authUser = findUserByEmailOrUsernameUserOrThrow(request.getEmailOrUsername());

		if (!passwordEncoder.matches(request.getPassword(), authUser.getPassword())) {
			throw new AuthException(AuthErrorType.AUTH_INVALID_PASSWORD, request.getEmailOrUsername());

		}

		String accessToken = jwtService.generateAccessToken(authUser);
		long accessTokenExpiresIn = jwtService.getAccessTokenExpiration();
		RefreshToken refreshToken = refreshTokenService.create(authUser, request.getIpAddress(),
				request.getUserAgent());

		return authMapper.toLoginResponse(authUser, accessToken, accessTokenExpiresIn, refreshToken.getToken(),
				refreshTokenService.getRefreshTokenExpiration());
	}

	@Override
	@Transactional(readOnly = true)
	public AuthUserResponse getAdminByUsername(String username) {
		
		AuthUser admin = findUserByEmailOrUsernameUserOrThrow(username);
		

		return authMapper.toAuthUserResponse(admin);
	}

	@Override
	@Transactional(readOnly = true)
	public AuthUserResponse getUserById(Long id) {
		AuthUser authUser = findUserByIdOrThrow(id);

		return authMapper.toAuthUserResponse(authUser);
	}

	@Override
	@Transactional(readOnly = true)
	public AuthUserResponse getUserByUsernameOrEmail(String usernameOrEmail) {
		AuthUser authUser = findUserByEmailOrUsernameUserOrThrow(usernameOrEmail);

		return authMapper.toAuthUserResponse(authUser);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AuthUserResponse> getAllUsers() {
		List<AuthUser> authUsers = authUserRepository.findAll();

		List<AuthUserResponse> authUserResponses = new ArrayList<>();

		for (AuthUser authUser : authUsers) {
			AuthUserResponse authUserResponse = authMapper.toAuthUserResponse(authUser);
			authUserResponses.add(authUserResponse);
		}
		return authUserResponses;
	}

	@Override
	@Transactional
	public AuthUserResponse updateUserById(AuthUserUpdateRequest authUserUpdateRequest, Long id) {
		AuthUser authUser = findUserByIdOrThrow(id);

		return updateUser(authUserUpdateRequest, authUser);

	}

	@Override
	@Transactional
	public AuthUserResponse updateUserByUsernameOrEmail(AuthUserUpdateRequest updateRequest, String usernameOrEmail) {

		AuthUser authUser = findUserByEmailOrUsernameUserOrThrow(usernameOrEmail);

		return updateUser(updateRequest, authUser);

	}

	@Override
	@Transactional
	public void deleteAdminByUsername(String username) {
		AuthUser admin = findUserByEmailOrUsernameUserOrThrow(username);

		authUserRepository.delete(admin);

	}

	@Override
	@Transactional
	public void deleteUserById(Long id) {
		AuthUser authUser = findUserByIdOrThrow(id);

		authUserRepository.delete(authUser);

	}

	@Override
	@Transactional
	public void deleteUserByUsernameOrEmail(String usernameOrEmail) {
		AuthUser authUser = findUserByEmailOrUsernameUserOrThrow(usernameOrEmail);

		authUserRepository.delete(authUser);

	}

	
	@Override
	@Transactional(readOnly = true)
	public AuthUserResponse getCurrentUser(String username) {
		AuthUser authUser = findUserByEmailOrUsernameUserOrThrow(username);
		
		return authMapper.toAuthUserResponse(authUser);
	}

	@Override
	@Transactional
	public AuthUserResponse updateCurrentUser(AuthUserUpdateRequest updateRequest, String username) {
		AuthUser authUser = findUserByEmailOrUsernameUserOrThrow(username);

		return updateUser(updateRequest, authUser);

	}

	@Override
	@Transactional
	public void deleteCurrentUser(String username) {
		AuthUser authUser = findUserByEmailOrUsernameUserOrThrow(username);

		authUserRepository.delete(authUser);

	}

	private AuthUserResponse updateUser(AuthUserUpdateRequest updateRequest, AuthUser authUser) {
		authMapper.updateAuthUser(updateRequest, authUser);

		return authMapper.toAuthUserResponse(authUser);

	}

	private AuthUser findUserByEmailOrUsernameUserOrThrow(String emailOrUsername) {
		return authUserRepository.findByUsernameOrEmail(emailOrUsername)
				.orElseThrow(() -> new AuthException(AuthErrorType.AUTH_USER_NOT_FOUND, emailOrUsername));
	}

	private AuthUser findUserByIdOrThrow(Long id) {
		return authUserRepository.findById(id)
				.orElseThrow(() -> new AuthException(AuthErrorType.AUTH_USER_NOT_FOUND, id));
	}

	
}
