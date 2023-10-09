package com.suw0n.tabmark.tabmark.application.query.response;

import com.suw0n.tabmark.tabmark.domain.entity.Attachment;
import com.suw0n.tabmark.tabmark.domain.entity.TabMark;

import java.time.LocalDateTime;
import java.util.List;

public record TabMarkInfoResponse(
        long id,
        String title,
        String link,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        List<AttachmentInfoResponse> attachmentList) {
    public static TabMarkInfoResponse of(TabMark tabMark) {
        return new TabMarkInfoResponse(tabMark.getId(), tabMark.getTitle(), tabMark.getLink(),
                tabMark.getCreatedAt(), tabMark.getModifiedAt(), AttachmentInfoResponse.asList(tabMark.getAttachmentSet()));
    }

    public static List<TabMarkInfoResponse> asList(List<TabMark> tabMarkList) {
        return tabMarkList.stream()
                .map(TabMarkInfoResponse::of)
                .toList();
    }

    public static List<TabMarkInfoResponse> of(List<Attachment> attachmentList) {
        return asList(attachmentList.stream()
                .map(Attachment::getTabMark)
                .toList());
    }
}