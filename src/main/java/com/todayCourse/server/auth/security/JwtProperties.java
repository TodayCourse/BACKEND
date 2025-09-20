package com.todayCourse.server.auth.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@ConfigurationProperties(prefix = "jwt")
@Getter @Setter
public class JwtProperties {
    private String secret;
    private long accessExpiration;
    private long refreshExpiration;

    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);  // secret를 Base64로 저장
        return Keys.hmacShaKeyFor(keyBytes);
    }
}