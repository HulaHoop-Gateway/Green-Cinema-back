package com.novacinema.cinemaFranchise.model.dao;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
// 데이터베이스의 영화관 지점(T_CinemaFranchise) 테이블에 접근하여 데이터를 조작하는 매퍼 인터페이스
@Mapper
public interface CinemaFranchiseMapper {
    // 시스템에 등록된 전체 영화관 지점 목록을 조회하여 반환한다
    List<CinemaFranchiseDTO> findAllCinemaFranchises();
    
    // 신규 영화관 지점 정보를 데이터베이스에 삽입한다
    void addCinemaFranchise(CinemaFranchiseDTO cinemaFranchiseDTO);
    
    // 주어진 지점 번호를 기준으로 해당 영화관 지점 정보를 데이터베이스에서 삭제한다
    void deleteCinemaFranchiseByBranchNum(int branchNum);
    
    // 기존 영화관 지점의 정보(이름, 주소 등)를 갱신한다
    void updateCinemaFranchise(CinemaFranchiseDTO cinemaFranchiseDTO);
}
