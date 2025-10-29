package com.novacinema.reservation.controller;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.service.CinemaFranchiseService;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservation.model.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // ✅ 바로 여기!
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController( ReservationService reservationService){
        this.reservationService = reservationService;
    }
    /*db에서 얻어온 값  수신*/
    @GetMapping("/list")
    public ResponseEntity<List<ReservationDTO>> getReservationDTOList() {
        List<ReservationDTO> reservationList=reservationService.getAllReservations();
        return ResponseEntity.ok(reservationList);
    }
    @GetMapping("/list3")
    public ResponseEntity<List<ReservationDTO>> getReservationDTOList3() {
        List<ReservationDTO> reservationList=reservationService.getAllReservations();
        return ResponseEntity.ok(reservationList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable int id) {
        System.out.println("test");
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ReservationDTO>> getReservationHistory(@RequestParam int userCode) {
        List<ReservationDTO> reservationList = reservationService.getReservationByUserCode(userCode);
        return ResponseEntity.ok(reservationList);
    }
}
