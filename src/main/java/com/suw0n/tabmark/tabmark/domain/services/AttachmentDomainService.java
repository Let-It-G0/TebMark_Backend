package com.suw0n.tabmark.tabmark.domain.services;

import com.suw0n.tabmark.tabmark.domain.entity.TabMark;
import com.suw0n.tabmark.tabmark.domain.entity.Attachment;
import com.suw0n.tabmark.tabmark.domain.repository.AttachmentRepository;
import com.suw0n.tabmark.tag.domain.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachmentDomainService {

    private final AttachmentRepository attachmentRepository;

    public long create(TabMark tabMark, Tag tag) {
        Attachment attachment = attachmentRepository.save(Attachment.ExceptIdBuilder()
                .tabMark(tabMark)
                .tag(tag)
                .build());

        tabMark.addAttachment(attachment);

        return attachment.getId();
    }

}