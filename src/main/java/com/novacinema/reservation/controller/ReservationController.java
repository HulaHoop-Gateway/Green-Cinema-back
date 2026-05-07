package com.novacinema.reservation.controller;

import com.novacinema.reservation.model.dto.GroupedReservationDTO;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservation.model.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // 프론트엔드 도메인에서의 교차 출처 리소스 공유(CORS) 허용
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 시스템 내 등록된 전체 예약 목록을 데이터베이스에서 조회하여 반환한다
    @GetMapping("/list")
    public ResponseEntity<List<ReservationDTO>> getReservationDTOList() {
        List<ReservationDTO> reservationList = reservationService.getAllReservations();
        return ResponseEntity.ok(reservationList);
    }

    // 주어진 핸드폰 번호와 일치하는 사용자의 전체 예약 내역을 조회한다
    @GetMapping("/history")
    public ResponseEntity<List<ReservationDTO>> getReservationHistory(@RequestParam String phoneNumber) {
        List<ReservationDTO> reservationList = reservationService.getReservationsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(reservationList);
    }

    // 주어진 핸드폰 번호를 기준으로 사용자의 예약 내역을 결제 그룹(booking_group_id) 단위로 묶어서 반환한다
    @GetMapping("/history/grouped")
    public ResponseEntity<List<GroupedReservationDTO>> getGroupedReservationHistory(@RequestParam String phoneNumber) {
        List<GroupedReservationDTO> groupedList = reservationService.getGroupedReservationsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(groupedList);
    }

}
