package com.novacinema.SeatReservationId;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
@Controller
@RequestMapping("/seatReservation")
public class SeatReservationController {

    private final SeatReservationService seatReservationService;

    public SeatReservationController(SeatReservationService seatReservationService) {
        this.seatReservationService = seatReservationService;
    }

    // ✅ 좌석 예약 등록
    @PostMapping("/register")
    public String registerSeatReservation(@ModelAttribute SeatReservationDTO reservationDTO) {
        reservationDTO.setReserved(true);
        reservationDTO.setReservedAt(new Timestamp(System.currentTimeMillis()));
        seatReservationService.registerSeatReservation(reservationDTO);
        return "redirect:/seatReservation/list?scheduleNum=" + reservationDTO.getScheduleNum();
    }

    // ✅ 특정 상영 일정의 예약된 좌석 목록 조회
    @GetMapping("/list")
    public String getReservedSeats(@RequestParam("scheduleNum") int scheduleNum, Model model) {
        List<SeatReservationDTO> reservedSeats = seatReservationService.getReservedSeatsBySchedule(scheduleNum);
        model.addAttribute("reservedSeats", reservedSeats);
        model.addAttribute("scheduleNum", scheduleNum);
        return "seatReservation/reservedSeats"; // 뷰 파일 경로
    }

    // ✅ 예매 취소 처리
    @PostMapping("/cancel")
    public String cancelReservation(@RequestParam("reservationId") int reservationId,
                                    @RequestParam("scheduleNum") int scheduleNum) {
        seatReservationService.changeReservationStatus(reservationId, false);
        return "redirect:/seatReservation/list?scheduleNum=" + scheduleNum;
    }

    // ✅ 특정 좌석의 예약 여부 확인 (AJAX 등에서 활용 가능)
    @GetMapping("/check")
    @ResponseBody
    public boolean checkSeatReserved(@RequestParam("scheduleNum") int scheduleNum,
                                     @RequestParam("seatCode") int seatCode) {
        SeatReservationDTO reservation = seatReservationService.getReservationByScheduleAndSeat(scheduleNum, seatCode);
        return reservation != null && reservation.isReserved();
    }
}

