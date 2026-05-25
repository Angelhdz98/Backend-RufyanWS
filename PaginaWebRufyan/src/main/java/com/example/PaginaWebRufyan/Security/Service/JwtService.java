package com.example.PaginaWebRufyan.Security.Service;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {
 private final String secretKey;
 private final long jwtExpiration;
 private final long refreshExpiration;

    public JwtService(
            @Value("${application.security.jwt.secret-key}") String secretKey,
            @Value("${application.security.jwt.expiration}") long expirationMs,
            @Value("${application.security.jwt.refresh-token.expiration}") long refreshExpiration
    ) {
        this.secretKey = secretKey;
        this.jwtExpiration = expirationMs;
        this.refreshExpiration=refreshExpiration;
    }

 public String generateToken(final UserDomain userDomain){
     return buildToken(userDomain, jwtExpiration);
 }
 public String generateExpiredToken(final UserDomain userDomain){
     return buildToken(userDomain, (jwtExpiration+1)*-1);
 }
 public String generateRefreshToken(final UserDomain userDomain){
     return  buildToken(userDomain, refreshExpiration);
 }
 private String buildToken(final  UserDomain userDomain, final Long expiration){

        Map<String, Object> claims = new HashMap<>();
        claims.put("name",userDomain.getFullname().getFullName());
        claims.put("role",userDomain.getRole().toString());

return Jwts.builder()
        .setId(UUID.randomUUID().toString())
        .addClaims(claims)
        .setSubject(userDomain.getEmail())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+expiration))
        .signWith(getSignInKey())
        .compact();
    }



    private SecretKey getSignInKey(){
     byte[] keyBytes = Decoders.BASE64.decode(secretKey);
     return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(final String token) {
        try {
            return extractClaims(token).getSubject();
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return e.getClaims().getSubject();

        }
    }
    public Claims extractClaims(final String token){
     return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }
    public String extractRole(String token){
     Claims claims =  Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
     return  claims.get("role", String.class);
    }

    public boolean isTokenValid(final String token, final  UserDomain userDomain){
        try {
            final String email  = extractEmail(token);
            return email.equals(userDomain.getEmail()) && !isTokenExpired(token);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            return false;

        }
    }

    private boolean isTokenExpired(String token) {
 return extractExpiration(token).before(new Date());
 }

    private Date extractExpiration(String token) {
     final Claims jwtToken = Jwts.parserBuilder()
             .setSigningKey(getSignInKey())
             .build()
             .parseClaimsJws(token)
             .getBody();
     return jwtToken.getExpiration();
    }
}
