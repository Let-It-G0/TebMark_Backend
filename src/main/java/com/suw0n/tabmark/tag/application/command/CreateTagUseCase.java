package com.suw0n.tabmark.tag.application.command;

import com.suw0n.tabmark.common.annotations.CommandUseCase;
import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.member.domain.services.MemberDomainService;
import com.suw0n.tabmark.tag.domain.services.TagDomainService;
import lombok.RequiredArgsConstructor;

@CommandUseCase
@RequiredArgsConstructor
public class CreateTagUseCase {

    private final TagDomainService tagDomainService;
    private final MemberDomainService memberDomainService;

    public long create(CreateTagCommand command) {
        Member member = memberDomainService.getMember();

        return tagDomainService.create(command.name(), member);
    }

}