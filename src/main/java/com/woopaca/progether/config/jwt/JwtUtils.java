package com.woopaca.progether.config.jwt;

import com.woopaca.progether.entity.User;
import com.woopaca.progether.exception.user.impl.UserNotFoundException;
import com.woopaca.progether.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final UserRepository userRepository;

    @Value("${jwt.secret_key}")
    private String secretKey;

    @PostConstruct
    void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String getEmailInToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public User getUserOfToken(String token) {
        String email = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException());
    }

    public String resolveToken(String authorization) {
        if (authorization == null) {
            return null;
        }

        if (!authorization.startsWith("Bearer ")) {
            throw new IllegalArgumentException();
        }

        return authorization.split(" ")[1];
    }

    public boolean isNotExpired(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);

        return !claims.getBody()
                .getExpiration()
                .before(new Date());
    }
}
