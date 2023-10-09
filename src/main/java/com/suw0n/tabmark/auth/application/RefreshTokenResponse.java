package com.suw0n.tabmark.auth.application;

public record RefreshTokenResponse(
        String accessToken) {
    public static RefreshTokenResponse of(String accessToken) {
        return new RefreshTokenResponse(accessToken);
    }
}