package com.chomdan.shared.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by foresight on 17. 8. 2.
 */
@Component
public class TokenAdminProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt.key}")
    private String signedKey;

    public boolean isValid (String tokenKey) {
        try {
            if (tokenKey.contains("Bearer")) {
                tokenKey = tokenKey.substring(7);
            }
            Jwts.parser().setSigningKey(signedKey.getBytes()).parseClaimsJws(tokenKey);
            return true;

        } catch (SignatureException e) {
            System.out.println(" ERROR !!!!");
            e.printStackTrace();
            return false;
        }
    }

    public Claims getClaims(String tokenKey) {
        if (tokenKey.contains("Bearer")) {
            tokenKey = tokenKey.substring(7);
        }
        return Jwts.parser().setSigningKey(signedKey.getBytes()).parseClaimsJws(tokenKey).getBody();
    }


    public String getTenantId(String tokenKey) {
        if (tokenKey.contains("Bearer")) {
            tokenKey = tokenKey.substring(7);
        }
        return Jwts.parser().setSigningKey(signedKey.getBytes()).parseClaimsJws(tokenKey).getBody().get("tenant_id").toString();
    }

    public String getUserId(String tokenKey) {
        if (tokenKey.contains("Bearer")) {
            tokenKey = tokenKey.substring(7);
        }
        return Jwts.parser().setSigningKey(signedKey.getBytes()).parseClaimsJws(tokenKey).getBody().get("user_id").toString();
    }

    public String getUserName(String tokenKey) {
        if (tokenKey.contains("Bearer")) {
            tokenKey = tokenKey.substring(7);
        }
        return Jwts.parser().setSigningKey(signedKey.getBytes()).parseClaimsJws(tokenKey).getBody().get("user_name").toString();
    }

    public String getUserRole(String tokenKey) {
        if (tokenKey.contains("Bearer")) {
            tokenKey = tokenKey.substring(7);
        }
        return Jwts.parser().setSigningKey(signedKey.getBytes()).parseClaimsJws(tokenKey).getBody().get("user_role").toString();
    }
}
