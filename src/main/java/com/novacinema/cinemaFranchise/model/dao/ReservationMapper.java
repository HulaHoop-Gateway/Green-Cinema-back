package com.novacinema.cinemaFranchise.model.dao;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDTO> selectAllReservations();
}
