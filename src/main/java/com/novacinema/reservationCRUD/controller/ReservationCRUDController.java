package com.novacinema.reservationCRUD.controller;

import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservationCRUD.service.ReservationCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationCRUDController {

    @Autowired
    private ReservationCRUDService reservationCRUDService;

    /**
     * 예매 및 좌석 예약을 동시에 처리하는 엔드포인트
     */
//    @PostMapping("/insert")
//    public ResponseEntity<String> insertReservation(
//            @RequestBody ReservationRequest request
//    ) {
//        try {
//            reservationCRUDService.reserveSeatAndInsertReservation(
//                    request.getReservationDTO(),
//                    request.getSeatReservationDTO()
//            );
//            return ResponseEntity.ok("예매 및 좌석 예약이 완료되었습니다.");
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("예매 처리 중 오류 발생: " + e.getMessage());
//        }
//    }
    @PostMapping("/insert2")
    public ResponseEntity<String> insertReservation2(@RequestBody ReservationRequest request) {
        try {
            reservationCRUDService.reserveSeatAndInsertReservation(request.getReservationDTO(), request.getSeatReservationDTO());
            return ResponseEntity.ok("예매 및 좌석 예약이 완료되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("예매 실패: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("예매 처리 중 오류 발생: " + e.getMessage());
        }
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateReservation(@RequestParam String reservationNum) {
        try {
            boolean updated = reservationCRUDService.updateReservationState(reservationNum);
            if (updated) {
                return ResponseEntity.ok("예매 상태가 성공적으로 수정되었습니다.");
            } else {
                return ResponseEntity.status(404).body("해당 예약을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("예매 수정 중 오류 발생: " + e.getMessage());
        }
    }





    /**
     * 클라이언트에서 두 DTO를 함께 전달받기 위한 래퍼 클래스
     */
    public static class ReservationRequest {
        private ReservationDTO reservationDTO;
        private SeatReservationDTO seatReservationDTO;

        public ReservationDTO getReservationDTO() {
            return reservationDTO;
        }

        public void setReservationDTO(ReservationDTO reservationDTO) {
            this.reservationDTO = reservationDTO;
        }

        public SeatReservationDTO getSeatReservationDTO() {
            return seatReservationDTO;
        }

        public void setSeatReservationDTO(SeatReservationDTO seatReservationDTO) {
            this.seatReservationDTO = seatReservationDTO;
        }
    }
}

