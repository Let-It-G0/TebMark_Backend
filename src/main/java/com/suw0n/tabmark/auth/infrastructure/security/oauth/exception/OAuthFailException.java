package com.suw0n.tabmark.auth.infrastructure.security.oauth.exception;

import com.suw0n.tabmark.common.exception.CustomException;

public class OAuthFailException extends CustomException {

    public static final CustomException EXCEPTION = new OAuthFailException();

    private OAuthFailException() {
        super(500, "OAuth failed");
    }

}