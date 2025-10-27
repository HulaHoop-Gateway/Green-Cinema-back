package com.novacinema.cinemaFranchise.model.dto;

public class TheatherDTO {
    private int screeningNum;      // 상영관 고유 번호
    private int screeningNumber;   // 상영관 번호
    private int branchNum;         // 지점 번호
    public TheatherDTO(){};

    public TheatherDTO(int screeningNum, int screeningNumber, int branchNum) {
        this.screeningNum = screeningNum;
        this.screeningNumber = screeningNumber;
        this.branchNum = branchNum;
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

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
        this.branchNum = branchNum;
    }

    @Override
    public String toString() {
        return "TheatherDTO{" +
                "screeningNum=" + screeningNum +
                ", screeningNumber=" + screeningNumber +
                ", branchNum=" + branchNum +
                '}';
    }
}
