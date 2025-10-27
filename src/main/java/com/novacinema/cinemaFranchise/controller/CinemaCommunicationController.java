package com.novacinema.cinemaFranchise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinemafranchise")
public class CinemaCommunicationController {
    @GetMapping("/communication1")
    public String redirectToCommunicationPage() {
        return "/cinemafranchise/communication1";
    }
    @GetMapping("/communication2")
    public String redirectToCommunicationPage2() {
        return "/cinemafranchise/communication2";
    }

}
