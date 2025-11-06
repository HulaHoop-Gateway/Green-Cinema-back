package com.novacinema.reservation.model.dao;

import com.novacinema.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDTO> selectAllReservations();
    List<ReservationDTO> selectReservationsByMemberCode();
    int insertReservation(ReservationDTO reservationDTO);


    int updateReservationState(@Param("reservationNum") String reservationNum,
                               @Param("state") String newState);

    ReservationDTO getReservationBySeatAndSchedule(int seatNumber, int scheduleNum);

    String findMaxReservationIdForToday(String s);
}