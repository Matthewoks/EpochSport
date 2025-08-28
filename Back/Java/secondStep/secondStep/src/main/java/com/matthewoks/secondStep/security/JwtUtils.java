package com.matthewoks.secondStep.security;

import com.matthewoks.secondStep.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.Value;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.exp irationms}")
    private Long jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Date now = new Date();
        Date exp = new Date((now).getTime() + jwtExpirationMs);
        userPrincipal.setExpirationTime(exp);

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())).setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact(); //aggiungo chiave segreta+codifica
    }

    //valida il token
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(authToken);
            return true;

        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {}" + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token {}" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("token is expired {}" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("token is unsupported {}" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("claims string is empty {}" + e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token) {
       return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
    }
}
