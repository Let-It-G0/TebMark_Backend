package com.suw0n.tabmark.tag.application.query;

import com.suw0n.tabmark.tabmark.domain.entity.Attachment;
import com.suw0n.tabmark.tag.domain.entity.Tag;

import java.util.List;
import java.util.Set;

public record TagInfoResponse(
        long id,
        String name) {
    public static TagInfoResponse of(Attachment tagTabMark) {
        return new TagInfoResponse(tagTabMark.getTag().getId(), tagTabMark.getTag().getName());
    }

    public static TagInfoResponse of(Tag tag) {
        return new TagInfoResponse(tag.getId(), tag.getName());
    }

    public static List<TagInfoResponse> asList(Set<Attachment> tagTabMarkSet) {
        return tagTabMarkSet.stream()
                .map(TagInfoResponse::of)
                .toList();
    }
}