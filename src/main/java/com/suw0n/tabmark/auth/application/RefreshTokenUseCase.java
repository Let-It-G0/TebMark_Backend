package com.suw0n.tabmark.auth.application;

import com.suw0n.tabmark.auth.infrastructure.jwt.consts.JwtType;
import com.suw0n.tabmark.auth.infrastructure.jwt.helper.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCase {

    private final JwtHelper jwtHelper;

    public RefreshTokenResponse refresh(final String refreshToken) {
        final Jws<Claims> claims = jwtHelper.getClaimsFromRefreshToken(refreshToken);

        jwtHelper.isWrongType(claims, JwtType.REFRESH);

        final String accessToken = jwtHelper.generateAccessToken(claims.getBody().getSubject());

        return RefreshTokenResponse.of(accessToken);
    }

}