package com.suw0n.tabmark.tabmark.domain.repository;

import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.tabmark.domain.entity.TabMark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabMarkRepository extends JpaRepository<TabMark, Long> {

    Page<TabMark> findByMember(Member member, Pageable pageable);

    Page<TabMark> searchByTitleContainingAndMember(String title, Member member, Pageable pageable);

    Optional<TabMark> findByIdAndMember(long id, Member member);

    Optional<TabMark> findByLinkAndMember(String link, Member member);

    Page<TabMark> findByMemberAndAttachmentSetEmpty(Member member, Pageable pageable);

}