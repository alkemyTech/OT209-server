package com.alkemy.ong.auth.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.alkemy.ong.exception.JwtBadRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${ong.jwt-secret}")
	private String jwtSecret;

	@Value("${ong.jwt-expiration-milliseconds}")
	private int jwtExpirationMs;

	public String generateToken(Authentication authentication) {

		String email = authentication.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + this.jwtExpirationMs);

		String token = Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;
	}

	public String getJWTUsername(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new JwtBadRequestException(HttpStatus.BAD_REQUEST, "Invalid JWT.");
		} catch (MalformedJwtException ex) {
			throw new JwtBadRequestException(HttpStatus.BAD_REQUEST, "Invalid JWT.");
		} catch (ExpiredJwtException ex) {
			throw new JwtBadRequestException(HttpStatus.BAD_REQUEST, "Expired JWT.");
		} catch (UnsupportedJwtException ex) {
			throw new JwtBadRequestException(HttpStatus.BAD_REQUEST, "Incompatible JWT.");
		} catch (IllegalArgumentException ex) {
			throw new JwtBadRequestException(HttpStatus.BAD_REQUEST, "Empty JWT claims.");
		}
	}
}
