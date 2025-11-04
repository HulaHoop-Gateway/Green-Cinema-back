package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDTO> selectAllReservations();
    int insertReservation(ReservationDTO reservationDTO);


    int updateReservationState(@Param("reservationNum") int reservationNum,
                               @Param("state") String newState);

    ReservationDTO getReservationBySeatAndSchedule(int seatNumber, int scheduleNum);
}