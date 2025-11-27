package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.register.RegisterRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.register.RegisterResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.exception.AuthErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.exception.AuthException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.mapper.AuthMapper;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.repository.AuthUserRepository;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.config.OtpTokenProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.model.OtpToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.service.OtpTokenService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.service.VerificationTokenService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.relation.userrole.model.UserRole;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.exception.RoleErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.exception.RoleException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.model.Role;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.repository.RoleRepository;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.type.PredefinedRole;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService  {

    private final OtpTokenProperties otpTokenProperties;

	private final AuthUserRepository authUserRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	private final AuthMapper authMapper;
	
	private final VerificationTokenService verificationTokenService;

	private final OtpTokenService otpTokenService;
		
	private final LoginResponseFactory loginResponseFactory;

    
	@Transactional
	public RegisterResponse register(RegisterRequest request) {
		throwIfUserExists(request);

		AuthUser authUser = buildAuthUserWithDefaultRole(request);
		AuthUser savedAuthUser = authUserRepository.save(authUser);

		verificationTokenService.createVerificationTokenAndSendEmail(savedAuthUser);

		RegisterResponse registerResponse = authMapper.toRegisterResponse(savedAuthUser);
		registerResponse.setMessage("Please verify your email!!!");

		return registerResponse;
	}

	public LoginResponse login(LoginRequest request) {
		AuthUser authUser = validateLogin(request.getEmailOrUsername() , request.getPassword());
		
		
		if(authUser.isTwoFactorEnabled()) {
			OtpToken otpToken = otpTokenService.createOtpToken(authUser , request , TokenPurpose.TWO_FACTOR_AUTH);
									
			otpTokenService.sendEmail(otpToken);
			
			return LoginResponse.builder()
					.otpRequired(true)
					.otpToken(otpToken.getOtpToken())
					.otpExpiresInSeconds(otpTokenProperties.getTtlSeconds())
					.message("Two-factor authentication required. Check your email for the OTP code.")
					.build();
		}
				
		
		
		return loginResponseFactory.createSuccessfulLoginResponse(authUser,request.getIpAddress(), request.getUserAgent());
		
		
	}

	private AuthUser validateLogin(String emailOrUsername, String password) {
		
		AuthUser authUser = authUserRepository.findByUsernameOrEmail(emailOrUsername)
				.orElseThrow(() -> new AuthException(AuthErrorType.AUTH_USER_NOT_FOUND));
		
		if(authUser.isDeletedFlag()) {
			throw new AuthException(AuthErrorType.AUTH_USER_NOT_FOUND);
		}
		
		if(!passwordEncoder.matches(password , authUser.getPassword())){
			throw new AuthException(AuthErrorType.AUTH_INVALID_PASSWORD);
		}

		if(!authUser.isEnabled()) {
			throw new AuthException(AuthErrorType.AUTH_USER_NOT_VERIFIED);
		}
		return authUser;
	}

	private AuthUser buildAuthUserWithDefaultRole(RegisterRequest request) {
		AuthUser authUser = AuthUser.builder()
				.username(request.getUsername())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).build();

		Role role = getRoleOrThrow(PredefinedRole.USER);

		UserRole userRoles = UserRole.builder().authUser(authUser).role(role).build();

		authUser.getUserRoles().add(userRoles);

		return authUser;
	}

	private Role getRoleOrThrow(PredefinedRole predefinedRole) {
		String roleName = predefinedRole.name();

		return roleRepository.findByName(roleName)
				.orElseThrow(() -> new RoleException(RoleErrorType.ROLE_NOT_FOUND, roleName));
	}

	private void throwIfUserExists(RegisterRequest request) {
		if (authUserRepository.findByUsername(request.getUsername()).isPresent()) {
			throw new AuthException(AuthErrorType.AUTH_USERNAME_ALREADY_EXISTS, request.getUsername());

		}
		if (authUserRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new AuthException(AuthErrorType.AUTH_EMAIL_ALREADY_EXISTS, request.getEmail());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return authUserRepository.findByUsername(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

	
	
}
