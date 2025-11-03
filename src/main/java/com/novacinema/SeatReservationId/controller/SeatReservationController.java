package com.novacinema.SeatReservationId.controller;

import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.SeatReservationId.model.service.SeatReservationService;
import com.novacinema.seat.model.dto.SeatDTO;
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

    public SeatReservationController(SeatReservationService seatReservationService) {
        this.seatReservationService = seatReservationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<SeatReservationDTO>> getSeatReservationDTOList() {
        List<SeatReservationDTO> seatReservationDTOS = seatReservationService.getAllSeatReservations();
        return ResponseEntity.ok(seatReservationDTOS);
    }
    @GetMapping("/available")
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(@RequestParam int scheduleNum) {
        List<SeatDTO> availableSeats = seatReservationService.getAvailableSeats(scheduleNum);
        return ResponseEntity.ok(availableSeats);
    }




}
