package com.novacinema.seat.model.dao;


import com.novacinema.seat.model.dto.SeatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeatMapper {
    List<SeatDTO> selectAllSeats();
    List<SeatDTO> selectAllSeatsBySchedule(@Param("scheduleNum") int scheduleNum);

}
