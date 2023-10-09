package com.suw0n.tabmark.tag.domain.services;

import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.member.domain.exception.NotMatchedMemberException;
import com.suw0n.tabmark.tag.domain.entity.Tag;
import com.suw0n.tabmark.tag.domain.exception.TagNotFoundException;
import com.suw0n.tabmark.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagDomainService {

    private final TagRepository tagRepository;

    public long create(String name, Member member) {
        return tagRepository.findByNameAndMember(name, member)
                .orElseGet(() -> tagRepository.save(Tag.ExceptIdBuilder()
                        .name(name)
                        .member(member)
                        .build())).getId();
    }

    public void delete(long id, Member member) {
        Tag tag = getByIdAndMember(id, member)
                .orElseThrow(() -> TagNotFoundException.EXCEPTION);

        tagRepository.delete(tag);
    }

    public Tag checkAuthor(long id, Member member) {
        return getByIdAndMember(id, member)
                .orElseThrow(() -> NotMatchedMemberException.EXCEPTION);
    }

    private Optional<Tag> getByIdAndMember(long id, Member member) {
        return tagRepository.findByIdAndMember(id, member);
    }

}