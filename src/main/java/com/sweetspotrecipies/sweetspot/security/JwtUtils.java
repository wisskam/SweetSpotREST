package com.sweetspotrecipies.sweetspot.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.sweetspotrecipies.sweetspot.security.BookUserDetails;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${auth.jwtSecret}")
    private String jwtSecret;
    @Value("${auth.jwtExpirationMs}")
    private int jwtExpirationMs;
    public String generateJwtToken(Authentication authentication) {
        BookUserDetails userPrincipal = (BookUserDetails)
                authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String getUserNameFromJwtToken(String token) {
        return
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject(
                );
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature", ex);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token", ex);
        } catch (ExpiredJwtException ex) {
            logger.error("JWT token is expired", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT token is unsupported", ex);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty", ex);
        }
        return false;
    }
}
