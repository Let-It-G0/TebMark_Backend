package com.suw0n.tabmark.common.dtos.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PagingRequest(
        int offset,
        int limit) {
    public Pageable withSort() {
        validateValues();

        return PageRequest.of(offset-1, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Pageable exceptSort() {
        validateValues();

        return PageRequest.of(offset-1, limit, Sort.by(Sort.Direction.DESC, "id"));
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