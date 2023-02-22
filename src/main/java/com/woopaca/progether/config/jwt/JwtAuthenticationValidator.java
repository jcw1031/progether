package com.woopaca.progether.config.jwt;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationValidator {

    private final JwtUtils jwtUtils;

    public boolean validateToken(String token) {
        if (token == null) {
            return false;
        }

        try {
            jwtUtils.isNotExpired(token);
        } catch (JwtException e) {
            return false;
        }

        String email = jwtUtils.getEmailInToken(token);
        log.info("sign-in success! Email = {}", email);
        return true;
    }
}
