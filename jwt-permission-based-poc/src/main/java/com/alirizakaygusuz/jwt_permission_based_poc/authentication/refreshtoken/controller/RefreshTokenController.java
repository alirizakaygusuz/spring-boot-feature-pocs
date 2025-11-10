package com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.dto.RefreshTokenRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.dto.RefreshTokenResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.service.RefreshTokenService;
import com.alirizakaygusuz.jwt_permission_based_poc.common.controller.BaseResponseController;
import com.alirizakaygusuz.jwt_permission_based_poc.common.response.StandardApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RefreshTokenController extends BaseResponseController{

	private final RefreshTokenService refreshTokenService;

	@PostMapping("/refresh-token/rotate")
	public ResponseEntity<StandardApiResponse<RefreshTokenResponse>> rotateToken(
			@Valid @RequestBody RefreshTokenRequest request, HttpServletRequest httpServletRequest) {

		request.injectClientMetaData(httpServletRequest);

		return ok(refreshTokenService.rotateToken(request));

	}

	@PostMapping("/logout")
	public ResponseEntity<StandardApiResponse<String>> logout(@Valid @RequestBody RefreshTokenRequest request,
			HttpServletRequest httpServletRequest) {
		request.injectClientMetaData(httpServletRequest);

		return ok(refreshTokenService.logout(request));

	}

}
