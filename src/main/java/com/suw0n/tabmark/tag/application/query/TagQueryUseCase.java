package com.suw0n.tabmark.tag.application.query;

import com.suw0n.tabmark.auth.infrastructure.security.helper.SecurityHelper;
import com.suw0n.tabmark.common.annotations.QueryUseCase;
import com.suw0n.tabmark.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryUseCase
@RequiredArgsConstructor
public class TagQueryUseCase {

    private final TagRepository tagRepository;
    private final SecurityHelper securityHelper;

    public List<TagInfoResponse> getByMember() {
        return tagRepository.findByMember(securityHelper.getCurrentMember()).stream()
                .map(TagInfoResponse::of)
                .toList();
    }

}