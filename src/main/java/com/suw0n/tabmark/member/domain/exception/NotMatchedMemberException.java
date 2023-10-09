package com.suw0n.tabmark.member.domain.exception;

import com.suw0n.tabmark.common.exception.CustomException;

public class NotMatchedMemberException extends CustomException {

    public static final CustomException EXCEPTION = new NotMatchedMemberException();

    private NotMatchedMemberException() {
        super(403, "Member does not matched");
    }

}