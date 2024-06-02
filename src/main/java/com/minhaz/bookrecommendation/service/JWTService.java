package com.minhaz.bookrecommendation.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000* 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256 )
                .compact();
    }

    public String generateRefreshToken(Map<String , Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(getSignKey(), SignatureAlgorithm.HS256 )
                .compact();
    }

    private Key getSignKey() {
//        https://www.devglan.com/online-tools/hmac-sha256-online
        byte[] key = Decoders.BASE64.decode("302a053b89ef112fba92add00940678462c528b2cd37e8e3e818b94689c189f9");
        return Keys.hmacShaKeyFor(key);
    }



    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    public String extractUserName(String token){
        return extractClaim(token, (claims -> claims.getSubject()));
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, (claims -> claims.getExpiration().before(new Date())));
    }


}
