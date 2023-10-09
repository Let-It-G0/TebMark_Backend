package com.suw0n.tabmark.tabmark.domain.entity;

import com.suw0n.tabmark.common.entity.BaseEntity;
import com.suw0n.tabmark.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tbl_tabmark")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TabMark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private final Set<Attachment> attachmentSet = new HashSet<>();

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public TabMark(String title, String link, Member member) {
        Objects.requireNonNull(title, "Title can not be null");
        Objects.requireNonNull(link, "Link can not be null");
        Objects.requireNonNull(member, "Member can not be null");

        this.title = title;
        this.link = link;
        this.member = member;
    }

    public void addAttachment(Attachment attachment) {
        attachmentSet.add(attachment);
    }

    public void removeAttachment(Attachment attachment) {
        attachmentSet.remove(attachment);
    }

}