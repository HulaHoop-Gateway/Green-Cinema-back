package com.novacinema.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TestController {
    @GetMapping("/test1")
    public String testPage1() {
        return "forward:/search.html";
    }
    @GetMapping("/test2")
    public String testPage2() {
        return "forward:/add.html";
    }
    @GetMapping("/test3")
    public String testPage3() {
        return "forward:/update.html";
    }
    @GetMapping("/test4")
    public String testPage4() {
        return "forward:/search2.html";
    }
    @GetMapping("/test5")
    public String testPage5() {
        return "forward:/add2.html";
    }

}
