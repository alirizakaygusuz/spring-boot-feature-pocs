package com.alirizakaygusuz.jwt_poc.common.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alirizakaygusuz.jwt_poc.auth.jwt.JwtService;
import com.alirizakaygusuz.jwt_poc.auth.jwt.exception.JwtCustomException;
import com.alirizakaygusuz.jwt_poc.auth.jwt.exception.type.JwtErrorType;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	private final UserDetailsService userDetailsService;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		


		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = header.substring(7);

		try {
			String userNameByToken = jwtService.getUsernameByToken(token);

			if (userNameByToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(userNameByToken);

				if (userDetails != null && jwtService.isTokenValid(token)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userNameByToken, null, userDetails.getAuthorities());

					authenticationToken.setDetails(userDetails);

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		} catch (ExpiredJwtException e) {
			throw new JwtCustomException(JwtErrorType.JWT_EXPIRED_TOKEN , e.getMessage());

		}
		
		filterChain.doFilter(request, response);

	}
}
