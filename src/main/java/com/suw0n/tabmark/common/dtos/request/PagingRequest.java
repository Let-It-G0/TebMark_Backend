package com.suw0n.tabmark.common.dtos.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PagingRequest(
        int offset,
        int limit) {
    public Pageable toPageable() {
        validateValues();

        return PageRequest.of(offset-1, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    private void validateValues() {
        if(offset < 1) {
            throw new IllegalArgumentException("Out of bound offset");
        }

        if(limit < 1) {
            throw new IllegalArgumentException("Out of bound size");
        }
    }
}