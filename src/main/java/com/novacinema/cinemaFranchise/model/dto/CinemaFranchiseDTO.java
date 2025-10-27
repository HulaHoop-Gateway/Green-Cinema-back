package com.novacinema.cinemaFranchise.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CinemaFranchiseDTO {
    @JsonProperty
    private int branchNum;
    @JsonProperty
    private String branchName;
    @JsonProperty
    private String address;
    public CinemaFranchiseDTO(){}

    public CinemaFranchiseDTO(int branchNum, String branchName, String address) {
        this.branchNum = branchNum;
        this.branchName = branchName;
        this.address = address;
    }

    public int getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(int branchNum) {
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
                "branchNum=" + branchNum +
                ", branchName='" + branchName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
