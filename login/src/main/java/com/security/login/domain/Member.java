package com.security.login.domain;

import lombok.Data;

@Data
public class Member {

    String memberId;
    String memberPw;
    String role;
}
