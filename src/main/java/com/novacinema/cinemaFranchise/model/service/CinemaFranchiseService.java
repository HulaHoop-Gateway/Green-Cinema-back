package com.novacinema.cinemaFranchise.model.service;


import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.dao.CinemaFranchiseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
// 영화관 지점(프랜차이즈) 정보 관리를 담당하는 서비스 클래스
@Service
public class CinemaFranchiseService {
    private final CinemaFranchiseMapper cinemaFranchiseMapper;
    public CinemaFranchiseService(CinemaFranchiseMapper cinemaFranchiseMapper){
        this.cinemaFranchiseMapper=cinemaFranchiseMapper;
    }
    // 전체 영화관 지점 목록을 데이터베이스에서 조회하여 반환한다
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
