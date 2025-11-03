package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDTO> selectAllReservations();
    int insertReservation(ReservationDTO reservationDTO);

}