package com.suw0n.tabmark.tabmark.domain.entity;

import com.suw0n.tabmark.tag.domain.entity.Tag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "tbl_attachment")
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tabmark_id")
    private TabMark tabMark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder(builderClassName = "ExceptIdBuilder", builderMethodName = "ExceptIdBuilder")
    public Attachment(TabMark tabMark, Tag tag) {
        Objects.requireNonNull(tabMark, "TabMark can not be null");
        Objects.requireNonNull(tag, "Tag can not be null");

        this.tabMark = tabMark;
        this.tag = tag;
    }

}