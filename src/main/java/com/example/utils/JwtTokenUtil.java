package com.example.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5  * 60 * 60;

	public static final long JWT_TOKEN_VALIDITY_FORGOT_PASS = 5 * 60;
	
	private static String secret="t>+l:Y%puW~oGl";
	
	
	
	
	public String getEmailFromToken(String token) {

		return getClaimFromToken(token, Claims::getSubject);

	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {

		return getClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);

	}

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {

		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());

	}
	

	// generate token for user
	public static String generateToken(UserDetails userDeatils) {

		Map<String, Object> claims = new HashMap<>();
		
		return doGenerateToken(claims, userDeatils.toString());

	}

	// generate token for user
	public static String generateTokenOnForgotPass(String email) {

		Map<String, Object> claims = new HashMap<>();
		return doGenerateTokenOnForgotPass(claims, email);

	}
//generate token when user login
	public static String generateTokenOnLogin(String email,String password) {
		Map<String ,Object> claims=new HashMap<>();
		return doGenerateTokenOnLogin(claims,email,password);
	}
	private static String doGenerateTokenOnLogin(Map<String, Object> claims,  String subject, String password) {
		System.out.println(claims + "   Subject: "+ subject + "   Password: " + password);
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000))).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	private static String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000))).signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	private static String doGenerateTokenOnForgotPass(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY_FORGOT_PASS * 1000))).signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {

		return !isTokenExpired(token);

		// throw new ResourceNotFoundException("Timeout for this request");
	}

}
