package com.novacinema.cinemaFranchise.controller;

import com.novacinema.cinemaFranchise.model.dto.ReservationDTO;
import com.novacinema.cinemaFranchise.model.service.CinemaFranchiseService;
import com.novacinema.cinemaFranchise.model.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    public ReservationController(ReservationService reservationService){
        this.reservationService=reservationService;
    }
    /*db에서 얻어온 값  수신*/
    @GetMapping("/list")
    public ResponseEntity<List<ReservationDTO>> getReservationList() {
        List<ReservationDTO> reservationDTOList= reservationService.getAllReservations();
        return ResponseEntity.ok(reservationDTOList);
    }



}
