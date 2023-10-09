package com.suw0n.tabmark.member.domain.services;

import com.suw0n.tabmark.auth.infrastructure.security.helper.SecurityHelper;
import com.suw0n.tabmark.member.domain.repository.MemberRepository;
import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.auth.infrastructure.security.model.MemberProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDomainService {

    private final MemberRepository memberRepository;
    private final SecurityHelper securityHelper;

    public Member saveOrUpdate(MemberProfile memberProfile) {
        Member member = memberRepository.findByEmail(memberProfile.email())
                .orElse(null);

        if(member == null) {
            return memberRepository.save(memberProfile.toMember());
        } else {
            member.updateOnLogin(memberProfile.name(), memberProfile.imgUrl());

            return member;
        }
    }

    public Member getMember() {
        return securityHelper.getCurrentMember();
    }

}