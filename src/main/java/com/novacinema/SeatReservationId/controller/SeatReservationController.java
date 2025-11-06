package com.novacinema.SeatReservationId.controller;

import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.SeatReservationId.model.service.SeatReservationService;
import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.seat.model.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seatReservation")
public class SeatReservationController {

    private final SeatReservationService seatReservationService;
    private  final SeatService seatService;

    public SeatReservationController(SeatReservationService seatReservationService, SeatService seatService) {
        this.seatReservationService = seatReservationService;
        this.seatService = seatService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SeatReservationDTO>> getSeatReservationDTOList() {
        List<SeatReservationDTO> seatReservationDTOS = seatReservationService.getAllSeatReservations();
        return ResponseEntity.ok(seatReservationDTOS);
    }
    // 예약 가능한 좌석
    @GetMapping("/available")
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(@RequestParam int scheduleNum) {
        List<SeatDTO> availableSeats = seatReservationService.getAvailableSeats(scheduleNum);
        return ResponseEntity.ok(availableSeats);
    }

    // 전체 좌석 조회
    @GetMapping("/all")
    public ResponseEntity<List<SeatDTO>> getAllSeats(@RequestParam int scheduleNum) {
        List<SeatDTO> allSeats = seatReservationService.getAllSeatsByScheduleNum(scheduleNum);
        return ResponseEntity.ok(allSeats);
    }
}
