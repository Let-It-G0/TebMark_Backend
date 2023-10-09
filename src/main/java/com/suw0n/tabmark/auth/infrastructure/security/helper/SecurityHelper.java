package com.suw0n.tabmark.auth.infrastructure.security.helper;

import com.suw0n.tabmark.auth.infrastructure.security.model.CustomMemberDetails;
import com.suw0n.tabmark.member.domain.entity.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityHelper {

    public Member getCurrentMember() {
        return ((CustomMemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMember();
    }

}