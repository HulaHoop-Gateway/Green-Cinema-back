package com.novacinema.reservation.controller;


import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservation.model.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/reservation")
public class ReservationController2 {
    private final ReservationService reservationService;

    public ReservationController2(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/regist")
    public String showReservationForm(Model model) {
        model.addAttribute("reservationDTO", new ReservationDTO());
        return "reservationRegist"; // templates/reservationRegist.html
    }

    @PostMapping("/register")
    public String registerReservation(@ModelAttribute ReservationDTO reservationDTO) {
        boolean success = reservationService.registerReservation(reservationDTO);
        return success ? "redirect:/reservation/success" : "redirect:/reservation/fail";
    }
}

