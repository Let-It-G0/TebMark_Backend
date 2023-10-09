package com.suw0n.tabmark.tabmark.domain.repository;

import com.suw0n.tabmark.tabmark.domain.entity.Attachment;
import com.suw0n.tabmark.tag.domain.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Page<Attachment> findByTag(Tag tag, Pageable pageable);

}