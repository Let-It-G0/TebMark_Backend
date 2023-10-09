package com.suw0n.tabmark.tag.domain.repository;

import com.suw0n.tabmark.member.domain.entity.Member;
import com.suw0n.tabmark.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByIdAndMember(long id, Member member);

    Optional<Tag> findByNameAndMember(String name, Member member);

    @EntityGraph(attributePaths = {"member"})
    List<Tag> findByMember(Member member);

}