package com.hotel.RoomBooking.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey key;
    private static final long EXPIRATION_TIME = (60*60*24)*1000;  //24 hours in millis

    //Class constructor to generate key for signing token
    public JwtUtil(@Value("${secret.string}") String secret_string) {
        String secretString = secret_string;
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");

    }

    //generate jwt token
    public String generateToken(UserDetails userDetails){

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();

    }

    //parse and extract data from jwt
    //private methods called only in this class
    //extract claims from token

    private Claims extractClaims(String jwtToken){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    //extract username from token
    public String extractUserName(String token){
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    //check if token is Expired
    public boolean isTokenExpired(String token){
        Claims claims = extractClaims(token);
        return claims.getExpiration().after(new Date());
    }


    //checking validity and expiration of jwt token
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUserName(token) ;
        return (username.equals(userDetails.getUsername())) && (isTokenExpired(token)) ;
    }


}
