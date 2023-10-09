package com.suw0n.tabmark.auth.infrastructure.security.model;

import com.suw0n.tabmark.auth.infrastructure.security.oauth.exception.WrongLoginTypeException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public enum OAuthAttributes {

    GOOGLE(LoginType.GOOGLE, (attributes) ->
            new MemberProfile(
                    (String) attributes.get("name"),
                    (String) attributes.get("email"),
                    (String) attributes.get("picture"))
    );

    private final LoginType registration;
    private final Function<Map<String, Object>, MemberProfile> of;

    public static MemberProfile toProfile(LoginType registration, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registration.equals(provider.registration))
                .findFirst()
                .orElseThrow(() -> WrongLoginTypeException.EXCEPTION)
                .of.apply(attributes);
    }

}