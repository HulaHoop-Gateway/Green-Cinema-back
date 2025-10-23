package com.ohgiraffers.com.cinemaFranchise;


import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CinemaFranchiseService {
    private final CinemaFranchiseMapper cinemaFranchiseMapper;
    public CinemaFranchiseService(CinemaFranchiseMapper cinemaFranchiseMapper){
        this.cinemaFranchiseMapper=cinemaFranchiseMapper;
    }
    public List<CinemaFranchiseDTO> getAllCinemaFranchises(){
        return cinemaFranchiseMapper.findAllCinemaFranchises();
    }
}
