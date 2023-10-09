package com.suw0n.tabmark.tag.domain.exception;

import com.suw0n.tabmark.common.exception.CustomException;

public class TagNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new TagNotFoundException();

    private TagNotFoundException() {
        super(404, "Tag not found");
    }

}