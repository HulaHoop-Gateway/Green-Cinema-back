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

// 특정 상영 일정에 대한 좌석 예약 현황 및 잔여 좌석을 조회하는 컨트롤러 창구
@RestController
@RequestMapping("/seatReservation")
public class SeatReservationController {

    private final SeatReservationService seatReservationService;
    private  final SeatService seatService;

    public SeatReservationController(SeatReservationService seatReservationService, SeatService seatService) {
        this.seatReservationService = seatReservationService;
        this.seatService = seatService;
    }

    // 시스템 내 등록된 전체 좌석 예약 데이터를 조회하여 반환한다
    @GetMapping("/list")
    public ResponseEntity<List<SeatReservationDTO>> getSeatReservationDTOList() {
        List<SeatReservationDTO> seatReservationDTOS = seatReservationService.getAllSeatReservations();
        return ResponseEntity.ok(seatReservationDTOS);
    }
    // 특정 상영 일정에서 예약되지 않은 사용 가능한 좌석 목록만 필터링하여 반환한다
    @GetMapping("/available")
    public ResponseEntity<List<SeatDTO>> getAvailableSeats(@RequestParam int scheduleNum) {
        List<SeatDTO> availableSeats = seatReservationService.getAvailableSeats(scheduleNum);
        return ResponseEntity.ok(availableSeats);
    }

    // 특정 상영 일정에 속한 모든 좌석의 상태(예약 여부 등)를 종합하여 반환한다
    @GetMapping("/all")
    public ResponseEntity<List<SeatDTO>> getAllSeats(@RequestParam int scheduleNum) {
        List<SeatDTO> allSeats = seatReservationService.getAllSeatsByScheduleNum(scheduleNum);
        return ResponseEntity.ok(allSeats);
    }
}
