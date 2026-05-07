package com.novacinema.theater.model.dto;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;

// 상영관 정보를 담아 계층 간 데이터를 전달하는 데이터 전송 객체
// 상영관의 위치와 소속 지점 정보를 포함하며, 데이터베이스의 T_Theater 테이블과 매핑된다
public class TheaterDTO {
    // 시스템에서 상영관을 식별하는 고유 키 번호
    private int screeningNum;       
    
    // 상영관 번호 (예: 1관, 2관)
    private int screeningNumber;    
    
    // 해당 상영관이 소속된 영화관 지점의 식별 번호 (외래키 역할)
    private String branchNum;          
    
    // 소속된 영화관 지점의 상세 정보를 포함하는 연관 DTO 객체
    private CinemaFranchiseDTO cinemaFranchisedto;

    // 기본 생성자
    public TheaterDTO() {}

    public TheaterDTO(int screeningNum, int screeningNumber, String branchNum, CinemaFranchiseDTO cinemaFranchisedto) {
        this.screeningNum = screeningNum;
        this.screeningNumber = screeningNumber;
        this.branchNum = branchNum;
        this.cinemaFranchisedto = cinemaFranchisedto;
    }

    public int getScreeningNum() {
        return screeningNum;
    }

    public void setScreeningNum(int screeningNum) {
        this.screeningNum = screeningNum;
    }

    public int getScreeningNumber() {
        return screeningNumber;
    }

    public void setScreeningNumber(int screeningNumber) {
        this.screeningNumber = screeningNumber;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public CinemaFranchiseDTO getCinemaFranchisedto() {
        return cinemaFranchisedto;
    }

    public void setCinemaFranchisedto(CinemaFranchiseDTO cinemaFranchisedto) {
        this.cinemaFranchisedto = cinemaFranchisedto;
    }

    @Override
    public String toString() {
        return "TheaterDTO{" +
                "screeningNum=" + screeningNum +
                ", screeningNumber=" + screeningNumber +
                ", branchNum='" + branchNum + '\'' +
                ", cinemaFranchisedto=" + cinemaFranchisedto +
                '}';
    }
}
