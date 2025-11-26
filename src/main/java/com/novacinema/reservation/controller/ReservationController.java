package com.novacinema.reservation.controller;

import com.novacinema.reservation.model.dto.GroupedReservationDTO;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservation.model.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // ✅ 프론트 연결 허용
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /** ✅ 전체 예약 목록 조회 */
    @GetMapping("/list")
    public ResponseEntity<List<ReservationDTO>> getReservationDTOList() {
        List<ReservationDTO> reservationList = reservationService.getAllReservations();
        return ResponseEntity.ok(reservationList);
    }

    /** ✅ 특정 사용자(핸드폰 번호 기준) 예약 내역 조회 */
    @GetMapping("/history")
    public ResponseEntity<List<ReservationDTO>> getReservationHistory(@RequestParam String phoneNumber) {
        List<ReservationDTO> reservationList = reservationService.getReservationsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(reservationList);
    }

    /** ✅ 특정 사용자(핸드폰 번호 기준) 예약 내역 조회 - 그룹화 */
    @GetMapping("/history/grouped")
    public ResponseEntity<List<GroupedReservationDTO>> getGroupedReservationHistory(@RequestParam String phoneNumber) {
        List<GroupedReservationDTO> groupedList = reservationService.getGroupedReservationsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(groupedList);
    }

}
