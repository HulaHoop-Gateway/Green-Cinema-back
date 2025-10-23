package com.ohgiraffers.com.cinemaFranchise;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CinemaFranchiseMapper {
    List<CinemaFranchiseDTO> findAllCinemaFranchises();
}
