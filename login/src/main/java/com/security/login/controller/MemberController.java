package com.security.login.controller;

import com.security.login.domain.Member;
import com.security.login.domain.UserMember;
import com.security.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/user")
    public void save(@RequestBody Member member) {
        member.setRole("ROLE_USER");
        memberService.save(member);
    }

    @GetMapping("/check")
    public void check(@AuthenticationPrincipal UserMember member) {
        log.info(member.getMember().getMemberId());
    }

}
