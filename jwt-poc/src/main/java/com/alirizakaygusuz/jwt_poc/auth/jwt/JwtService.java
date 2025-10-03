package com.alirizakaygusuz.jwt_poc.auth.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.jwt_poc.auth.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_poc.auth.jwt.exception.JwtCustomException;
import com.alirizakaygusuz.jwt_poc.auth.jwt.exception.type.JwtErrorType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

	@Value("${jwt.secret.key}")
	private String SECRET_KEY;

	@Value("${jwt.access.token.expiration-minutes}")
	private Long accessTokenExpirationMinutes;

	public String generateAccessToken(AuthUser authUser) {
		Map<String, Object> claims = new HashMap<>();	

		return Jwts.builder()
		    .setClaims(claims)
		    .setSubject(authUser.getUsername())
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpirationMinutes * 60 * 1000))
		    .signWith(getKey(), SignatureAlgorithm.HS256)
		    .compact();
	}

	public String getUsernameByToken(String token) {
		return extractClaimFromToken(token, Claims::getSubject);
	}

	public boolean isTokenValid(String token) {
	    try {
	        Claims claims = getClaims(token);
	        return new Date().before(claims.getExpiration());
	    } catch (ExpiredJwtException e) {
	        throw new JwtCustomException(JwtErrorType.JWT_EXPIRED_TOKEN, e.getMessage());
	    } catch (MalformedJwtException e) {
	    	throw new JwtCustomException(JwtErrorType.JWT_MALFORMED_TOKEN, e.getMessage());
	    } catch (UnsupportedJwtException e) {
	    	throw new JwtCustomException(JwtErrorType.JWT_UNSUPPORTED_TOKEN, e.getMessage());

	    } catch (Exception e) {
	    	throw new JwtCustomException(JwtErrorType.JWT_GENERAL_TOKEN_EXCEPTION, e.getMessage());
	    }
	}


	public <T> T extractClaimFromToken(String token, Function<Claims, T> claimsFunction) {
		Claims claims = getClaims(token);

		return claimsFunction.apply(claims);
	}

	public Claims getClaims(String token) {
		return Jwts.parserBuilder().
				setSigningKey(getKey()).
				build().
				parseClaimsJws(token).
				getBody();

	}

	public long getAccessTokenExpiresIn() {
        return accessTokenExpirationMinutes;
    }

	public Key getKey() {
		byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(bytes);
	}
}

