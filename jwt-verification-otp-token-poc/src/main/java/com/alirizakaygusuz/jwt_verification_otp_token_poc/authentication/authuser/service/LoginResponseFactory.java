package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.service;

import org.springframework.stereotype.Service;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.jwt.service.JwtService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.model.RefreshToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginResponseFactory {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public LoginResponse createSuccessfulLoginResponse(AuthUser authUser,
                                                       String ipAddress,
                                                       String userAgent) {

        String accessToken = jwtService.generateAccessToken(authUser);
        RefreshToken refreshToken = refreshTokenService.createAndSave(authUser, ipAddress, userAgent);

        return LoginResponse.builder()
                .otpRequired(false)
                .username(authUser.getUsername())
                .email(authUser.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }
}
