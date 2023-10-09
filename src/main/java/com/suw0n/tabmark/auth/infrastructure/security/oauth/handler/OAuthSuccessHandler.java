package com.suw0n.tabmark.auth.infrastructure.security.oauth.handler;

import com.suw0n.tabmark.auth.infrastructure.jwt.helper.JwtHelper;
import com.suw0n.tabmark.auth.infrastructure.security.model.CustomMemberDetails;
import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.auth.infrastructure.cookie.CookieAuthorizationRequestRepository;
import com.suw0n.tabmark.auth.infrastructure.cookie.CookieManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.suw0n.tabmark.auth.infrastructure.cookie.CookieAuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
@RequiredArgsConstructor
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtHelper jwtHelper;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final CookieManager cookieManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        clearAuthenticationAttributesAndCookies(request);

        super.getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        isNotMatchedUri(cookieManager.getCookie(REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue));

        Member member = ((CustomMemberDetails) authentication.getPrincipal()).getMember();

        String memberEmail = member.getEmail();

        final String accessToken = jwtHelper.generateAccessToken(memberEmail);
        final String refreshToken = jwtHelper.generateRefreshToken(memberEmail);

        return UriComponentsBuilder.fromUriString("http://localhost:3000/callback")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build().toUriString();
    }

    private void clearAuthenticationAttributesAndCookies(HttpServletRequest request) {
        super.clearAuthenticationAttributes(request);

        cookieAuthorizationRequestRepository.removeAuthorizationRequestCookies();
    }

    private void isNotMatchedUri(final Optional<String> redirectUri) {
        if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("redirect URIs are not matched");
        }
    }

    private boolean isAuthorizedRedirectUri(final String uri) {
        URI clientRedirectUri = URI.create(uri);
        URI authorizedUri = URI.create("/login/success");

        return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                && authorizedUri.getPort() == clientRedirectUri.getPort();
    }

}