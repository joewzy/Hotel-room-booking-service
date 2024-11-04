package com.hotel.RoomBooking.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private SecretKey key;
    private static final long EXPIRATION_TIME = (60*60*24)*1000;  //24 hours in millis

    //Class constructor to generate key for signing token
    public JwtUtil() {
        String secretString = "am9zaWFoIHRyeWluZyBvdXQgYSByb29tIHJlc2VydmF0aW9uIHNlcnZpY2UgaW1wbGVtZW50aW5nIGp3dCB3aXRoIHNwcmluZyBzZWN1cml0eQ==";
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

    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    //extract username from token
    public String extractUserName(String token){
        return extractClaims(token,Claims::getSubject);
    }

    //check if token is Expired
    public boolean isTokenExpired(String token){
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }


    //checking validity and expiration of jwt token
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUserName(token) ;
        return (username.equals(userDetails.getUsername())) && (!isTokenExpired(token)) ;
    }


}
