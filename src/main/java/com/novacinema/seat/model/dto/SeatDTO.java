package com.novacinema.seat.model.dto;

import java.math.BigDecimal;

public class SeatDTO {
    private int seatCode;         // 좌석 고유 코드
    private char seatRow;         // 좌석 행 (A, B, C 등)
    private int seatColumn;       // 좌석 열 (1, 2, 3 등)
    private String seatType;      // 좌석 종류 (일반, VIP 등)
    private String seatRealNum;   // 실제 좌석 번호 (예: A10)
    private BigDecimal sale;      // 가격
    private int screeningNum;     // 상영관 번호 (외래키)

    // 기본 생성자
    public SeatDTO() {}

    public SeatDTO(int seatCode, char seatRow, int seatColumn, String seatType, String seatRealNum, BigDecimal sale, int screeningNum) {
        this.seatCode = seatCode;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatType = seatType;
        this.seatRealNum = seatRealNum;
        this.sale = sale;
        this.screeningNum = screeningNum;
    }

    public int getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(int seatCode) {
        this.seatCode = seatCode;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(char seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getSeatRealNum() {
        return seatRealNum;
    }

    public void setSeatRealNum(String seatRealNum) {
        this.seatRealNum = seatRealNum;
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

    @Override
    public String toString() {
        return "SeatDTO{" +
                "seatCode=" + seatCode +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                ", seatType='" + seatType + '\'' +
                ", seatRealNum='" + seatRealNum + '\'' +
                ", sale=" + sale +
                ", screeningNum=" + screeningNum +
                '}';
    }
}
