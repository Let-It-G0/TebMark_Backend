package com.suw0n.tabmark.tabmark.application.query.response;

import com.suw0n.tabmark.tabmark.domain.entity.Attachment;

import java.util.List;
import java.util.Set;

public record AttachmentInfoResponse(
        long id,
        long tagId,
        String tagName) {
    public static AttachmentInfoResponse of(Attachment attachment) {
        return new AttachmentInfoResponse(attachment.getId(), attachment.getTag().getId(), attachment.getTag().getName());
    }

    public static List<AttachmentInfoResponse> asList(Set<Attachment> attachmentSet) {
        return attachmentSet.stream()
                .map(AttachmentInfoResponse::of)
                .toList();
    }
}