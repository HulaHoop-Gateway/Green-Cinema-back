package com.novacinema.seat.controller;

import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.seat.model.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/seat1")
public class SeatViewController {

    private final SeatService seatService;

    public SeatViewController(SeatService seatService) {
        this.seatService = seatService;
    }

    // 특정 상영 일정의 전체 좌석 조회 (뷰 렌더링)
    @GetMapping("/all")
    public String showAllSeats(@RequestParam("scheduleNum") int scheduleNum, Model model) {
        List<SeatDTO> seatList = seatService.getAllSeatsBySchedule(scheduleNum);
        model.addAttribute("seatList", seatList);
        return "seat/allSeats"; // templates/seat/allSeats.html
    }
}

