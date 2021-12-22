package com.practice.security.controller;

import com.practice.security.model.User;
import com.practice.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/"})
    public String index() {

        //mustache 기본폴더 src/main/resource
        //view resolver 설정 : templates (prefix), .mustache(suffix)
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user) {
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);

        userRepository.save(user); // 회원가입 잘됨. 비밀번호 :1234 => 시큐리티로 로그인을 할 수 없음 패스워드 암호화가 되지 않았음
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN") // 특정 메소드에 권한 부여하는 역할, 하나만 걸고 싶으면 적당함
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANGAER') or hasRole('ROLE_ADMIN')") // data 메소드가 실행되기 직전에 확인, 여러개 줄 때 사용
    @GetMapping("/data")
    public @ResponseBody String data() {
        return "개인정보";
    }

}
