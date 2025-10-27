package com.novacinema.cinemaFranchise.model.dto;

public class SeatDTO {
    private int seatCode;         // 좌석 고유 코드 (PK)
    private String seatRow;       // 좌석 행 (예: A, B, C)
    private int seatColumn;       // 좌석 열 (예: 1, 2, 3)
    private String seatType;      // 좌석 종류 (예: 일반, 커플, 장애인석)
    private String seatRealNum;   // 실제 좌석 번호 (예: A10)
    private double sale;          // 가격
    private int screeningNum;// 상영관 번호 (FK)
    public SeatDTO(){}

    public SeatDTO(int seatCode, String seatRow, int seatColumn, String seatType, String seatRealNum, double sale, int screeningNum) {
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

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
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

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
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
                ", seatRow='" + seatRow + '\'' +
                ", seatColumn=" + seatColumn +
                ", seatType='" + seatType + '\'' +
                ", seatRealNum='" + seatRealNum + '\'' +
                ", sale=" + sale +
                ", screeningNum=" + screeningNum +
                '}';
    }
}
