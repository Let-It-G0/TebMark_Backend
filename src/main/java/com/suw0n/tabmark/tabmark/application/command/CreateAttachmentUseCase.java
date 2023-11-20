package com.suw0n.tabmark.tabmark.application.command;

import com.suw0n.tabmark.common.annotations.CommandUseCase;
import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.member.domain.services.MemberDomainService;
import com.suw0n.tabmark.tabmark.domain.entity.TabMark;
import com.suw0n.tabmark.tabmark.domain.services.TabMarkDomainService;
import com.suw0n.tabmark.tabmark.domain.services.AttachmentDomainService;
import com.suw0n.tabmark.tag.domain.entity.Tag;
import com.suw0n.tabmark.tag.domain.services.TagDomainService;
import lombok.RequiredArgsConstructor;

@CommandUseCase
@RequiredArgsConstructor
public class CreateAttachmentUseCase {

    private final AttachmentDomainService attachmentDomainService;
    private final TabMarkDomainService tabMarkDomainService;
    private final TagDomainService tagDomainService;
    private final MemberDomainService memberDomainService;

    public long create(CreateAttachmentCommand command) {
        Member member = memberDomainService.getMember();

        TabMark tabMark = tabMarkDomainService.checkAuthor(command.tabMarkId(), member);

        Tag tag = tagDomainService.checkAuthor(command.tagId(), member);

        return attachmentDomainService.create(tabMark, tag);
    }

}