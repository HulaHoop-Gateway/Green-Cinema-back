package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.cinemaFranchise.model.dao.ReservationMapper;
import com.novacinema.cinemaFranchise.model.dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservationService {
   private final ReservationMapper reservationMapper;
    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }
    public  List<ReservationDTO> getAllReservations(){return reservationMapper.selectAllReservations();}

}
