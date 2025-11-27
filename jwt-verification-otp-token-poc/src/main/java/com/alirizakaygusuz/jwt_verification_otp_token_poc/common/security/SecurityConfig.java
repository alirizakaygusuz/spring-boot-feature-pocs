package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	private final AuthenticationProvider authenticationProvider;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	public static final String REGISTER = "/api/v1/auth/register";

	public static final String REFRESH_ROTATE = "/api/v1/auth/refresh-token/rotate";
	
	public static final String LOGIN = "/api/v1/auth/login";
	public static final String LOGOUT = "/api/v1/auth/logout";
	
	public static final String VERIFICATION_TOKEN_VERIFY = "/api/v1/auth/verification-token/verify";
	public static final String OTP_TOKEN_VERIFY = "/api/v1/auth/otp-token/verify";
	
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					
				.requestMatchers(REGISTER,REFRESH_ROTATE, LOGIN,LOGOUT,VERIFICATION_TOKEN_VERIFY , OTP_TOKEN_VERIFY).permitAll()
				.anyRequest().authenticated()
			)
			
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(ex -> ex
	                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401
	                .accessDeniedHandler(jwtAccessDeniedHandler)           // 403
	            )
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.build();
	}
	

}
