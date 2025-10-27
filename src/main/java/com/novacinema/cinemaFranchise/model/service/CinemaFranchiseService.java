package com.novacinema.cinemaFranchise.model.service;


import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.dao.CinemaFranchiseMapper;
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

    public void addCinemaFranchise(CinemaFranchiseDTO cinemaFranchiseDTO) {
        cinemaFranchiseMapper.addCinemaFranchise(cinemaFranchiseDTO);
    }

    public void deleteByBranchNum(int branchNum) {
        cinemaFranchiseMapper.deleteCinemaFranchiseByBranchNum(branchNum);}

    public void updateCinemaFranchise(CinemaFranchiseDTO cinemaFranchiseDTO) {
        cinemaFranchiseMapper.updateCinemaFranchise(cinemaFranchiseDTO);
    }
}
