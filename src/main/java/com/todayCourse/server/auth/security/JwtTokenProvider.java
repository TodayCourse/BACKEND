package com.todayCourse.server.auth.security;

import com.todayCourse.server.user.service.CustomUserDetailsService;
import com.todayCourse.server.constant.type.Role;
import com.todayCourse.server.exception.BusinessLogicException;
import com.todayCourse.server.exception.ExceptionCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties props;
    private final StringRedisTemplate redis;
    private final CustomUserDetailsService customUserDetailsService;

    private Key key() {
        // 수정: secret은 충분히 긴 바이트 열이어야 함 (운영에서는 env로 주입)
        return props.getSigningKey();
    }

    // Access Token 생성 (roles 포함)
    public String createAccessToken(String email, Set<Role> roles) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + props.getAccessExpiration());
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();
    }

    // Refresh Token 생성 (Redis에 저장)
    public String createRefreshToken(String email) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + props.getRefreshExpiration());
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key(), SignatureAlgorithm.HS512)
                .compact();

        // Redis에 저장 (key: RT:{email})
        redis.opsForValue().set("RT:" + email, token, props.getRefreshExpiration(), TimeUnit.MILLISECONDS);
        return token;
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // UserDetailService 를 통해 user 를 다시 로드함으로써 권한 동기화
            String email = claims.getSubject();
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        } catch (JwtException | IllegalArgumentException e) {
            throw new BusinessLogicException(ExceptionCode.INVALID_TOKEN);
        }
    }

    public Optional<String> getStoredRefreshToken(String email) {
        return Optional.ofNullable(redis.opsForValue().get("RT:" + email));
    }

    public void deleteRefreshToken(String email) {
        redis.delete("RT:" + email);
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public long getExpiration(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody();
        return claims.getExpiration().getTime();
    }

    // Redis 블랙리스트 등록
    public void blacklistAccessToken(String token) {
        long expiration = getExpiration(token) - System.currentTimeMillis();
        if (expiration > 0) {
            String key = "BL:" + sha256(token);
            redis.opsForValue().set(key, "logout", expiration, TimeUnit.MILLISECONDS);
        }
    }

    // 블랙리스트 확인
    public boolean isBlacklisted(String token) {
        String key = "BL:" + sha256(token);
        return Optional.ofNullable(redis.hasKey(key)).orElse(false);
    }

    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
