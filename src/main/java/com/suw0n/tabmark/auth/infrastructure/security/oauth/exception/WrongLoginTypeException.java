package com.suw0n.tabmark.auth.infrastructure.security.oauth.exception;

import com.suw0n.tabmark.common.exception.CustomException;

public class WrongLoginTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongLoginTypeException();

    private WrongLoginTypeException() {
        super(403, "Login Type is wrong");
    }

}