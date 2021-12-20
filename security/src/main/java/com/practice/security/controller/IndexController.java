package com.practice.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"","/"})
    public String index() {

        //mustache 기본폴더 src/main/resource
        //view resolver 설정 : templates (prefix), .mustache(suffix)
        return "index";
    }
}
