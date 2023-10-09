package com.suw0n.tabmark.member.application;

import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.member.domain.services.MemberDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberUseCase {

    private final MemberDomainService memberDomainService;

    public Member get() {
        return memberDomainService.getMember();
    }

}