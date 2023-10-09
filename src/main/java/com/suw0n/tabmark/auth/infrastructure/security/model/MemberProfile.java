package com.suw0n.tabmark.auth.infrastructure.security.model;

import com.suw0n.tabmark.member.domain.entity.Member;

public record MemberProfile(
        String name,
        String email,
        String imgUrl) {
    public Member toMember() {
        return Member.ExceptIdBuilder()
                .name(name)
                .email(email)
                .imgUrl(imgUrl)
                .build();
    }
}