package com.springproject.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.springproject.core.domain.UserDomain;
import com.springproject.exception.InvalidGenericException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	private String jwtSigningKey = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public boolean hasClaim(String token, String claimName) {
		final Claims claims = extractAllClaims(token);
		return claims.get(claimName) != null;
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(token).getBody();
	}

	public String generateToken(UserDomain userDomain) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDomain);
	}

	public String generateToken(UserDomain userDomain, Map<String, Object> claims) {
		return createToken(claims, userDomain);
	}

	private String createToken(Map<String, Object> claims, UserDomain userDomain) {
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, jwtSigningKey)
				.setSubject(userDomain.getUsername()).claim("authorities", userDomain.getAuthorities())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24))).compact();
	}

	public Boolean isValidToken(String token, UserDomain userDomain) {
		if (!userDomain.getCurrentToken().equals(token))
			throw new InvalidGenericException("Invalid token!");
		final String username = extractUsername(token);
		return (username.equals(userDomain.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public static UserDomain userLogged() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()
				|| authentication.getPrincipal().equals("anonymousUser")) {
			return null;
		}
		return (UserDomain) authentication.getPrincipal();
	}
}
