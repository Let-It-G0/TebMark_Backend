package com.suw0n.tabmark.member.ui;

import com.suw0n.tabmark.member.application.GetMemberUseCase;
import com.suw0n.tabmark.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final GetMemberUseCase getMemberUseCase;

    @GetMapping("/my")
    public Member getMember() {
        return getMemberUseCase.get();
    }

}