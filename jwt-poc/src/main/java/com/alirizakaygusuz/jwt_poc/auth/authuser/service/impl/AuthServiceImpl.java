package com.alirizakaygusuz.jwt_poc.auth.authuser.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.LoginRequest;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.LoginResponse;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterRequest;
import com.alirizakaygusuz.jwt_poc.auth.authuser.dto.RegisterResponse;
import com.alirizakaygusuz.jwt_poc.auth.authuser.exception.AuthException;
import com.alirizakaygusuz.jwt_poc.auth.authuser.exception.type.AuthErrorType;
import com.alirizakaygusuz.jwt_poc.auth.authuser.mapper.AuthMapper;
import com.alirizakaygusuz.jwt_poc.auth.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_poc.auth.authuser.repository.AuthUserRepository;
import com.alirizakaygusuz.jwt_poc.auth.authuser.service.AuthService;
import com.alirizakaygusuz.jwt_poc.auth.jwt.JwtService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService , UserDetailsService{

	private final AuthUserRepository authUserRepository;
	private final AuthMapper authMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	 
	@Override
	@Transactional
	public RegisterResponse register(RegisterRequest request) {
		
		throwExceptionIfUserExists(request.getUsername() , request.getEmail());
		
		AuthUser authUser = authMapper.toEntity(request);
		
		authUser.setPassword(passwordEncoder.encode(request.getPassword()));

		
		AuthUser savedAuthUser = authUserRepository.save(authUser);
		

		return authMapper.toRegisterResponse(savedAuthUser);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public LoginResponse login(LoginRequest request) {
		AuthUser authUser =	findUserOrThrowExceptionIfNot(request.getEmailOrUsername());
		
		if(!passwordEncoder.matches(request.getPassword() , authUser.getPassword())){
	        throw new AuthException(AuthErrorType.AUTH_INVALID_PASSWORD, request.getEmailOrUsername());

		}
		
		String accessToken= jwtService.generateAccessToken(authUser);
	    long expiresIn = jwtService.getAccessTokenExpiresIn();

		
		
		return authMapper.toLoginResponse(authUser , accessToken ,expiresIn);
	}

	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.findByUsernameOrEmail(username)
                .orElseThrow(() -> new AuthException(AuthErrorType.AUTH_USER_NOT_FOUND, username));
    }
	
	
	private void throwExceptionIfUserExists(String username , String email) {
		if(authUserRepository.existsByUsername(username)) {
			throw new AuthException(AuthErrorType.AUTH_USERNAME_ALREADY_EXISTS , username);
		}
		
		if(authUserRepository.existsByEmail(email)) {
			throw new AuthException(AuthErrorType.AUTH_EMAIL_ALREADY_EXISTS , email);
		}
	}
	
	private AuthUser findUserOrThrowExceptionIfNot(String emailOrUsername) {
		return authUserRepository.findByUsernameOrEmail(emailOrUsername).orElseThrow(
				() -> new AuthException(AuthErrorType.AUTH_USER_NOT_FOUND , emailOrUsername)
				);
	}

	
	
	
	
	
}
