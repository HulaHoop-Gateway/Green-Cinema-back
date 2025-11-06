package com.novacinema.SeatReservationId.model.dao;

import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SeatReservationMapper {

    List<SeatReservationDTO> getAllReservations();

    void insertSeatReservation(SeatReservationDTO seatReservationDTO);

    int updateSeatReservedFlag(@Param("reservationId") String reservationId,
                               @Param("reserved") boolean reserved);

    List<SeatDTO> getAllSeatsByScheduleNum(@Param("scheduleNum") int scheduleNum);
    List<SeatDTO> getAvailableSeatsBySchedule(@Param("scheduleNum") int scheduleNum);
}


