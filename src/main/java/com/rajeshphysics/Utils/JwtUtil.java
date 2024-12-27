package com.rajeshphysics.Utils;

import java.util.*;

import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.rajeshphysics.Payloads.AppConstrants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil{
	
	private String secret = "rajeshphysics";
	
	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

//	-----------it will generate token for 3 days ------------------
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateTokenFree(claims, userDetails.getUsername()); 
	}
	
	private String doGenerateTokenFree(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+AppConstrants.JWT_TOKEN_VALIDITY * 1000 ))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	

	
	//validation
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username= getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	

//	------------------ it will generate according to no of days------------------
	public String generateTokenDays(UserDetails userDetails, Long days) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateTokenDays(claims, userDetails.getUsername(), days); 
	}
	private String doGenerateTokenDays(Map<String, Object> claims, String subject, Long days) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ days*24*60*60*1000 ))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
}
