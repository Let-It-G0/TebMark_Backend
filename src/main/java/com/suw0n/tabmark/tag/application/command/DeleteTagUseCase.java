package com.suw0n.tabmark.tag.application.command;

import com.suw0n.tabmark.common.annotations.CommandUseCase;
import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.member.domain.services.MemberDomainService;
import com.suw0n.tabmark.tag.domain.services.TagDomainService;
import lombok.RequiredArgsConstructor;

@CommandUseCase
@RequiredArgsConstructor
public class DeleteTagUseCase {

    private final TagDomainService tagDomainService;
    private final MemberDomainService memberDomainService;

    public void delete(long id) {
        Member member = memberDomainService.getMember();

        tagDomainService.delete(id, member);
    }

}