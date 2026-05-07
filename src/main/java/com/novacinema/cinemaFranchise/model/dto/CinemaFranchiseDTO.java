package com.novacinema.cinemaFranchise.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

// 영화관 지점(프랜차이즈)의 기본 정보를 담는 데이터 전송 객체
// 데이터베이스의 T_CinemaFranchise 테이블과 매핑되어 지점 조회 등에 활용된다
public class CinemaFranchiseDTO {
    // 특정 지점을 식별하는 고유 번호 (PK)
    private String branchNum;      
    
    // 지점의 상호명 (예: 강남점, 신촌점)
    private String branchName;     
    
    // 지점의 상세 주소 (예매 및 위치 기반 조회 시 사용)
    private String address;        

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
