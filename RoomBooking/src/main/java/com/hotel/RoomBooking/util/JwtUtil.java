package com.hotel.RoomBooking.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class JwtUtil {

    //generate jwt token
    private String generateToken(Map<String, Object> extraClaims, UserDetails details){
        return Jwts.builder().claims(extraClaims).subject(details.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getSigningKey()).compact();
    }

    //generate key for signing token
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("am9zaWFoIHRyeWluZyBvdXQgYSByb29tIHJlc2VydmF0aW9uIHNlcnZpY2UgaW1wbGVtZW50aW5nIGp3dCB3aXRoIHNwcmluZyBzZWN1cml0eQ==");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //checking validity and expiration of jwt token
    //parse and extract data from jwt
    //private methods called only in this class

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey()
    }


}
