package com.suw0n.tabmark.member.domain.exception;

import com.suw0n.tabmark.common.exception.CustomException;

public class MemberNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new MemberNotFoundException();

    private MemberNotFoundException() {
        super(404, "Member not found");
    }

}