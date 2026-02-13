package com.ajemi.barber.Ta7li9_app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ajemi.barber.Ta7li9_app.entity.User;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtils {

    // 1. S-sarout d l-app: Khassu i-koun s3ib y-t-guessed
    private String jwtSecret = "Ta7li9aAppSecretKey2026_Unique_Ajemi_Security_Key";
    
    // 2. L-moda d s-sala7iya: 24 sa3a (b l-milliseconde)
    private int jwtExpirationMs = 86400000;

    // --- A. Génération dyal Token ---
    public String generateJwtToken(User user) {
        // "Claims" huma l-ma3loumat l-iḍafiya li ghadi n-khbiw f l-token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());       // Zdna l-ID bach y-koun unique
        claims.put("role", user.getRole());   // Zdna l-role bach Angular i-3ref chno i-werri

        return Jwts.builder()
                .setClaims(claims)              // N-7etto l-ma3loumat dyalna
                .setSubject(user.getEmail())    // L-email howa l-huwiya d l-user
                .setIssuedAt(new Date())        // Weqtach t-creyat
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Weqtach t-tsala
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // N-signiw l-khliṭa b s-sarout
                .compact();
    }

    // --- B. I-stikhraj l-ma3loumat mn l-Token ---
    public String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // --- C. Tahqiq mn si7at l-Token (Validation) ---
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true; // Ila dkhlat hna, rah l-token s7i7a
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false; // Ila waqe3 chi mouchkil, n-rddo false
    }
}
