package com.novacinema.cinemaFranchise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinemafranchise/communication")
public class CinemaPageController {
    @GetMapping
    public String redirectToCommunicationPage() {
        return "/cinemafranchise/communication";
    }


}
