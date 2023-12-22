package com.pariksha.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Service
public class JwtUtils {
	
//	 private String SECRET_KEY = "examportal";
	 
//     private final String secretKeyEncoded = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
     
//     SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyEncoded));
     
     SecretKey testKey = Jwts.SIG.HS256.key().build();
     
     String secretString = Encoders.BASE64.encode(testKey.getEncoded());

//     byte[] content = SECRET_KEY.getBytes(StandardCharsets.UTF_8);

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    private Claims extractAllClaims(String token) {
//	        return Jwts.parser().verifyWith(testKey).build().parseSignedClaims(token).getPayload();
	    	
	    	return Jwts.parser().setSigningKey(secretString.getBytes()).build().parseClaimsJws(token.replace("\"","")).getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        
	        return createToken(claims, userDetails.getUsername());
	    }

	    private String createToken(Map<String, Object> claims, String subject) {
	    	
	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	                .signWith(SignatureAlgorithm.HS256, secretString.getBytes()).compact();
	    	
//	    	return Jwts.builder()
//	    			.subject(subject)
//	    			.signWith(testKey)
//	    			.content(SECRET_KEY)
//	    			.compact();

	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

}
