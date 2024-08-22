package com.payment.payments.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtils {
    final String key = "ASDFSDFSD6455F456SDF564ADF54SD65SD5FSD54FSD5FSD5FDS5SD54D54F5W4EFE51WEFW51W5W5E1W5E1F5WE1FW5E6E5FEWF15WEF5EF65WEF";

    public String generateJwtToken(final String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 2);
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(calendar.getTime())
                .signWith(key())
                .compact();
    }

    public Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public String getJwtTokenFromHeader(final HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;

    }

    public String getUserNameFromJwtToken(final String jwtToken) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwtToken).getPayload().getSubject();
    }

    public boolean validateJwtToken(final String token) {
        Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token);
        return true;
    }
}
