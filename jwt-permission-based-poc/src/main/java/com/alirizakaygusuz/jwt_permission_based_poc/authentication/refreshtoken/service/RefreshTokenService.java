package com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.service;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.dto.RefreshTokenRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.dto.RefreshTokenResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.model.RefreshToken;

public interface RefreshTokenService {
	
	RefreshToken create(AuthUser authUser , String ip , String agent);
	
	RefreshTokenResponse rotateToken(RefreshTokenRequest request);
	
	String logout(RefreshTokenRequest request);
	
	Long getRefreshTokenExpiration();
	

}
