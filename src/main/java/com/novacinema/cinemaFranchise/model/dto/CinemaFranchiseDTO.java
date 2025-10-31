package com.novacinema.cinemaFranchise.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CinemaFranchiseDTO {
    private String branchNum;      // 지점 번호 (PK)
    private String branchName;     // 지점 이름
    private String address;        // 지점 주소

    public CinemaFranchiseDTO(){}

    public CinemaFranchiseDTO(String branchNum, String branchName, String address) {
        this.branchNum = branchNum;
        this.branchName = branchName;
        this.address = address;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CinemaFranchiseDTO{" +
                "branchNum='" + branchNum + '\'' +
                ", branchName='" + branchName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
