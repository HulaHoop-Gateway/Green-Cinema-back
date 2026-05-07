package com.novacinema.seat.controller;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.service.CinemaFranchiseService;
import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.seat.model.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 상영관 내 개별 좌석의 기본 정보를 조회하는 컨트롤러 창구
@RestController
@RequestMapping("/seat")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    // 데이터베이스에 저장된 전체 좌석 기준 정보를 조회하여 반환한다
    @GetMapping("/list")
    public ResponseEntity<List<SeatDTO>> getSeatDTOList() {
        List<SeatDTO> seatDTOList = seatService.getAllSeats();
        return ResponseEntity.ok(seatDTOList);
    }
    // 특정 상영 일정에 연결된 전체 좌석 정보를 조회하여 타임리프 템플릿(뷰)으로 전달한다
    @GetMapping("/all")
    public String showAllSeats(@RequestParam("scheduleNum") int scheduleNum, Model model) {
        List<SeatDTO> seatList = seatService.getAllSeatsBySchedule(scheduleNum);
        model.addAttribute("seatList", seatList);
        return "seat/allSeats";
    }





}
