package org.example.unidy_server.global.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.example.unidy_server.domain.member.entity.MemberEntity;
import org.example.unidy_server.domain.member.repository.MemberRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private final JwtProperties properties;
    private final SecretKey secretKey;
    private final MemberRepository memberRepository;
    private final UserDetailsService userDetailsService;

    public JwtUtils(JwtProperties properties, UserDetailsService userDetailsService, MemberRepository memberRespository) {
        this.properties = properties;
        this.secretKey = new SecretKeySpec(properties.getSecretKey().getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.memberRepository = memberRespository;
        this.userDetailsService = userDetailsService;
    }

    public String getUserId(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        return jws.getBody().getSubject(); // 사용자 ID 반환
    }

    public String getPassword(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("password", String.class);
    }

    public boolean isExpired(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public JwtInfo generateToken(MemberEntity member) {
        long now = new Date().getTime();

        String accessToken = Jwts.builder()
                .claim("password", member.getPw())
                .subject(member.getId())
                .issuedAt(new Date(now))
                .expiration(new Date(now + properties.getAccessExpired()))
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .claim("password", member.getPw()) // 비밀번호 포함
                .subject(member.getId())
                .issuedAt(new Date(now))
                .expiration(new Date(now + properties.getRefreshExpired()))
                .signWith(secretKey)
                .compact();

        return JwtInfo.of(accessToken, refreshToken);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String userId = getUserId(token); // ID를 가져오는 메서드
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}