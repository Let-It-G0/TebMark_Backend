package com.suw0n.tabmark.tabmark.domain.services;

import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.tabmark.domain.entity.TabMark;
import com.suw0n.tabmark.member.domain.exception.NotMatchedMemberException;
import com.suw0n.tabmark.tabmark.domain.exception.TabMarkNotFoundException;
import com.suw0n.tabmark.tabmark.domain.repository.TabMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TabMarkDomainService {

    private final TabMarkRepository tabMarkRepository;

    public long create(String title, String link, Member member) {
        return tabMarkRepository.findByLinkAndMember(link, member)
                .orElseGet(() -> tabMarkRepository.save(TabMark.ExceptIdBuilder()
                        .title(title)
                        .link(link)
                        .member(member)
                        .build())).getId();
    }

    public void delete(long id, Member member) {
        TabMark tabMark = getByIdAndMember(id, member)
                .orElseThrow(() -> TabMarkNotFoundException.EXCEPTION);

        tabMarkRepository.delete(tabMark);
    }

    public TabMark checkAuthor(long id, Member member) {
        return getByIdAndMember(id, member)
                .orElseThrow(() -> NotMatchedMemberException.EXCEPTION);
    }

    private Optional<TabMark> getByIdAndMember(long id, Member member) {
        return tabMarkRepository.findByIdAndMember(id, member);
    }

}