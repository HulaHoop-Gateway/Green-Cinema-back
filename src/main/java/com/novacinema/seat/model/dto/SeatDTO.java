package com.novacinema.seat.model.dto;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.theater.model.dto.TheaterDTO;

import java.math.BigDecimal;

public class SeatDTO {
    private int seatCode;           // 좌석 고유 코드
    private String seatType;        // 좌석 종류 (일반석, 커플석 등)
    private BigDecimal sale;        // 가격
    private int screeningNum;       // 상영관 번호 (FK)
    private int price;
    private int isAisle;
    private String rowLabel;
    private int colNum;


    private TheaterDTO theaterDTO;
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