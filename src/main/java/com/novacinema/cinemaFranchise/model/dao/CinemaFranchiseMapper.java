package com.novacinema.cinemaFranchise.model.dao;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CinemaFranchiseMapper {
    List<CinemaFranchiseDTO> findAllCinemaFranchises();
}
