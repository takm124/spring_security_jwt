package com.security.login.repository;

import com.security.login.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepo {

    private final Map<String, Member> store = new HashMap<>();
    private final BCryptPasswordEncoder passwordEncoder;

    String tmp;

    public void save(Member member) {
        member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
        log.info("저장 시 패스워드 {}", member.getMemberPw());
        tmp = member.getMemberPw();
        store.put(member.getMemberId(), member);
    }

    public Member findById(String id) {
        log.info("로그인 시 패스워드 {}", store.get(id).getMemberPw());
        boolean flag = tmp.equals(store.get(id).getMemberPw());
        log.info("비밀번호 체크 " + flag);
        return store.get(id);
    }
}
