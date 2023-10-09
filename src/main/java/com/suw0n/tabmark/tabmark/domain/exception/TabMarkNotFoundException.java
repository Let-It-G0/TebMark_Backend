package com.suw0n.tabmark.tabmark.domain.exception;

import com.suw0n.tabmark.common.exception.CustomException;

public class TabMarkNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new TabMarkNotFoundException();

    private TabMarkNotFoundException() {
        super(404, "TabMark not found");
    }

}