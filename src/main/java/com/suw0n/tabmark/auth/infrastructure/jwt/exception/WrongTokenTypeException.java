package com.suw0n.tabmark.auth.infrastructure.jwt.exception;

import com.suw0n.tabmark.common.exception.CustomException;

public class WrongTokenTypeException extends CustomException {

    public static final CustomException EXCEPTION = new WrongTokenTypeException();

    private WrongTokenTypeException() {
        super(400, "Token type is wrong");
    }

}