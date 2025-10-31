package com.novacinema.seat.controller;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.service.CinemaFranchiseService;
import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.seat.model.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    /*db에서 얻어온 값  전달*/
    @GetMapping("/list")
    public ResponseEntity<List<SeatDTO>> getSeatDTOList() {
        List<SeatDTO> seatDTOList = seatService.getAllSeats();
        return ResponseEntity.ok(seatDTOList);
    }
    @GetMapping("/all")
    public String showAllSeats(@RequestParam("scheduleNum") int scheduleNum, Model model) {
        List<SeatDTO> seatList = seatService.getAllSeatsBySchedule(scheduleNum);
        model.addAttribute("seatList", seatList);
        return "seat/allSeats";
    }





}
