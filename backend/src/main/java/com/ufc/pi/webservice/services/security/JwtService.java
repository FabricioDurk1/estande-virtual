package com.ufc.pi.webservice.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  private final String SECRET_KEY = "ab67a554e50b2655b61fa045024b0b939502fcb5d0edc48289545886a14d7a4a";


  public String generateToken(
    UserDetails userDetails
  ) {
    return generateToken(new HashMap<>(), userDetails);
  }


  public String generateToken(
    Map<String, Object> extraClaims,
    UserDetails userDetails
  ) {
    final long ONE_DAY_IN_MILLISECONDS = 86400000 ;

    Date tokenCreationDate =  new Date(System.currentTimeMillis());
    Date tokenExpirationDate = new Date(System.currentTimeMillis() + ONE_DAY_IN_MILLISECONDS);

    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(tokenCreationDate).setExpiration(tokenExpirationDate)
      .signWith(getSignInKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSignInKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  public boolean isTokenValid(String token, UserDetails userDetails){
    final String email = extractEmail(token);
    return (email.equals(userDetails.getUsername()) && isNotExpiredToken(token));
  }

  public boolean isNotExpiredToken(String token){
    return !extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token){
    return extractClaim(token, Claims::getExpiration);
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
