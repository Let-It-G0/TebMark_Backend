package com.suw0n.tabmark.auth.ui;

import com.suw0n.tabmark.auth.application.RefreshTokenResponse;
import com.suw0n.tabmark.auth.application.RefreshTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenUseCase refreshTokenUseCase;

    @PostMapping("/refresh")
    public RefreshTokenResponse refresh(@RequestBody RefreshTokenRequest request) {
        return refreshTokenUseCase.refresh(request.refreshToken());
    }

}