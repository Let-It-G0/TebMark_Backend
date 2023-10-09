package com.suw0n.tabmark.auth.infrastructure.jwt.helper;

import com.suw0n.tabmark.auth.infrastructure.jwt.properties.JwtProperties;
import com.suw0n.tabmark.auth.infrastructure.jwt.consts.JwtType;
import com.suw0n.tabmark.auth.infrastructure.jwt.exception.WrongTokenTypeException;
import com.suw0n.tabmark.auth.infrastructure.security.model.CustomMemberDetails;
import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.member.domain.exception.MemberNotFoundException;
import com.suw0n.tabmark.member.domain.repository.MemberRepository;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtHelper {

    private final MemberRepository memberRepository;
    private final JwtProperties jwtProperties;

    public String generateAccessToken(String email) {
        return generateToken(JwtType.ACCESS, email, jwtProperties.getAccessExpire(), jwtProperties.getAccessKey());
    }

    public String generateRefreshToken(String email) {
        return generateToken(JwtType.REFRESH, email, jwtProperties.getRefreshExpire(), jwtProperties.getSecretKey());
    }

    private String generateToken(JwtType jwtType, String email, long expire, String key) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, jwtType)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Jws<Claims> getClaimsFromAccessToken(final String accessToken) {
        return getClaims(accessToken, jwtProperties.getAccessKey());
    }

    public Jws<Claims> getClaimsFromRefreshToken(final String refreshToken) {
        return getClaims(refreshToken, jwtProperties.getSecretKey());
    }

    private Jws<Claims> getClaims(String token, String key) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(extractToken(token));
    }

    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaimsFromAccessToken(token);

        this.isWrongType(claims, JwtType.ACCESS);

        final Member member = memberRepository.findByEmail(claims.getBody().getSubject())
                .orElseThrow(() -> MemberNotFoundException.EXCEPTION);

        final CustomMemberDetails details = CustomMemberDetails.create(member);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public String extractTokenFromRequest(final HttpServletRequest request) {
        return extractToken(request.getHeader("Authorization"));
    }

    public void isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        if(!(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()))) {
            throw WrongTokenTypeException.EXCEPTION;
        }
    }

    private String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return token;
    }

}