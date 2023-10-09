package com.suw0n.tabmark.tabmark.application.command;

import com.suw0n.tabmark.common.annotations.CommandUseCase;
import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.member.domain.services.MemberDomainService;
import com.suw0n.tabmark.tabmark.domain.services.TabMarkDomainService;
import lombok.RequiredArgsConstructor;

@CommandUseCase
@RequiredArgsConstructor
public class CreateTabMarkUseCase {

    private final TabMarkDomainService tabMarkDomainService;
    private final MemberDomainService memberDomainService;

    public Long create(CreateTabMarkCommand command) {
        Member member = memberDomainService.getMember();

        return tabMarkDomainService.create(command.title(), command.link(), member);
    }

}