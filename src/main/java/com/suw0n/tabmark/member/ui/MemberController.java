package com.suw0n.tabmark.member.ui;

import com.suw0n.tabmark.member.application.GetMemberUseCase;
import com.suw0n.tabmark.member.domain.entity.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member API")
@RestController
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {

    private final GetMemberUseCase getMemberUseCase;

    @Operation(description = "내 회원 정보 조회")
    @GetMapping("/my")
    public Member getMember() {
        return getMemberUseCase.get();
    }

}