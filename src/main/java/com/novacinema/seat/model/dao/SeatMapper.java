package com.novacinema.seat.model.dao;


import com.novacinema.seat.model.dto.SeatDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeatMapper {
    List<SeatDTO> selectAllSeats();

}
