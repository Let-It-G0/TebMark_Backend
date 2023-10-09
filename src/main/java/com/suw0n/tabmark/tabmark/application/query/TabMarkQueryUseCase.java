package com.suw0n.tabmark.tabmark.application.query;

import com.suw0n.tabmark.auth.infrastructure.security.helper.SecurityHelper;
import com.suw0n.tabmark.common.annotations.QueryUseCase;
import com.suw0n.tabmark.common.dtos.request.PagingRequest;
import com.suw0n.tabmark.common.dtos.response.PageDataResponse;
import com.suw0n.tabmark.tabmark.application.query.response.TabMarkInfoResponse;
import com.suw0n.tabmark.tabmark.domain.entity.TabMark;
import com.suw0n.tabmark.tabmark.domain.entity.Attachment;
import com.suw0n.tabmark.tabmark.domain.repository.TabMarkRepository;
import com.suw0n.tabmark.tabmark.domain.repository.AttachmentRepository;
import com.suw0n.tabmark.tag.domain.entity.Tag;
import com.suw0n.tabmark.tag.domain.exception.TagNotFoundException;
import com.suw0n.tabmark.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@QueryUseCase
@RequiredArgsConstructor
public class TabMarkQueryUseCase {

    private final TabMarkRepository tabMarkRepository;
    private final AttachmentRepository attachmentRepository;
    private final TagRepository tagRepository;
    private final SecurityHelper securityHelper;

    /**
     * UseCase for getting TabMarkList order by createdAt
     * */
    public PageDataResponse<List<TabMarkInfoResponse>> getRecent(PagingRequest pagingRequest) {
        return new PageDataResponse<>(tabMarkRepository.findByMember(securityHelper.getCurrentMember(),
                        pagingRequest.withSort()).getContent().stream()
                .map(TabMarkInfoResponse::of)
                .toList());
    }

    /**
     * UseCase for searching TabMarkList by title
     * */
    public PageDataResponse<List<TabMarkInfoResponse>> searchByTitle(String title, PagingRequest pagingRequest) {
        return new PageDataResponse<>(tabMarkRepository.searchByTitleContainingAndMember(title, securityHelper.getCurrentMember(),
                pagingRequest.withSort()).getContent().stream()
                .map(TabMarkInfoResponse::of)
                .toList());
    }

    /**
     * UseCase for getting TabMarkList by Tag
     * */
    public PageDataResponse<List<TabMarkInfoResponse>> getByTag(PagingRequest pagingRequest, long tagId) {
        Tag tag = tagRepository.findByIdAndMember(tagId, securityHelper.getCurrentMember())
                .orElseThrow(() -> TagNotFoundException.EXCEPTION);

        List<Attachment> attachmentList = attachmentRepository.findByTag(tag, pagingRequest.exceptSort()).getContent();

        return new PageDataResponse<>(TabMarkInfoResponse.of(attachmentList));
    }

    /**
     * UseCase for getting TabMarkList by Tag
     * */
    public PageDataResponse<List<TabMarkInfoResponse>> getUntagged(PagingRequest pagingRequest) {
        List<TabMark> tabMarkList = tabMarkRepository.findByMemberAndAttachmentSetEmpty(
                securityHelper.getCurrentMember(),pagingRequest.exceptSort()).getContent();

        return new PageDataResponse<>(TabMarkInfoResponse.asList(tabMarkList));
    }

}