package com.novacinema.theater.model.dto;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;

public class TheaterDTO {
    private int screeningNum;       // 상영관 고유 번호
    private int screeningNumber;    // 상영관 번호
    private String branchNum;          // 지점 번호 (외래키)
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
