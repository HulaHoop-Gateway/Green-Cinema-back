package com.novacinema.seat.model.dto;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.theater.model.dto.TheaterDTO;

import java.math.BigDecimal;

// 상영관 내부의 개별 좌석에 대한 물리적 속성과 가격 정책을 담는 데이터 전송 객체
// 데이터베이스의 T_Seat 테이블과 매핑되며, 예매 시 사용자가 선택할 수 있는 객체 단위로 쓰인다
public class SeatDTO {
    // 시스템에서 좌석을 고유하게 식별하는 코드 번호
    private int seatCode;           
    
    // 좌석의 구분 유형 (일반석, 장애인석, 스위트박스 등)
    private String seatType;        
    
    // 이벤트나 조건에 따라 적용되는 할인 금액(비율) 정보
    private BigDecimal sale;        
    
    // 해당 좌석이 물리적으로 속해 있는 상영관의 식별 번호 (외래키)
    private int screeningNum;       
    
    // 좌석의 기본 결제 가격
    private int price;
    
    // 복도 측 좌석 여부를 나타내는 플래그 (0: 아님, 1: 맞음)
    private int isAisle;
    
    // 좌석의 행을 나타내는 알파벳 라벨 (예: 'A', 'B')
    private String rowLabel;
    
    // 좌석의 열을 나타내는 숫자 번호 (예: 1, 2)
    private int colNum;

    // 소속 상영관의 상세 정보를 담는 연관 DTO
    private TheaterDTO theaterDTO;
    
    // 연관된 상영 일정 정보를 담는 DTO
    private ScheduleDTO scheduleDTO;
    public SeatDTO() {
    }

    public SeatDTO(int seatCode, String seatType,  BigDecimal sale, int screeningNum, int price, int isAisle, String rowLabel, int colNum, TheaterDTO theaterDTO, ScheduleDTO scheduleDTO) {
        this.seatCode = seatCode;
        this.seatType = seatType;
        this.sale = sale;
        this.screeningNum = screeningNum;
        this.price = price;
        this.isAisle = isAisle;
        this.rowLabel = rowLabel;
        this.colNum = colNum;
        this.theaterDTO = theaterDTO;
        this.scheduleDTO = scheduleDTO;
    }

    public int getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(int seatCode) {
        this.seatCode = seatCode;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }



    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    public int getScreeningNum() {
        return screeningNum;
    }

    public void setScreeningNum(int screeningNum) {
        this.screeningNum = screeningNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIsAisle() {
        return isAisle;
    }

    public void setIsAisle(int isAisle) {
        this.isAisle = isAisle;
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public TheaterDTO getTheaterDTO() {
        return theaterDTO;
    }

    public void setTheaterDTO(TheaterDTO theaterDTO) {
        this.theaterDTO = theaterDTO;
    }

    public ScheduleDTO getScheduleDTO() {
        return scheduleDTO;
    }

    public void setScheduleDTO(ScheduleDTO scheduleDTO) {
        this.scheduleDTO = scheduleDTO;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "seatCode=" + seatCode +
                ", seatType='" + seatType + '\'' +
                ", sale=" + sale +
                ", screeningNum=" + screeningNum +
                ", price=" + price +
                ", isAisle=" + isAisle +
                ", rowLabel='" + rowLabel + '\'' +
                ", colNum=" + colNum +
                ", theaterDTO=" + theaterDTO +
                ", scheduleDTO=" + scheduleDTO +
                '}';
    }
}