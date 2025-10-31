package com.novacinema.seat.model.dto;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.theater.model.dto.TheaterDTO;

import java.math.BigDecimal;

public class SeatDTO {
    private int seatCode;           // 좌석 고유 코드
    private String seatType;        // 좌석 종류 (일반석, 커플석 등)
    private String seatRealNum;     // 실제 좌석 번호 (예: A1, B2)
    private BigDecimal sale;        // 가격
    private int screeningNum;       // 상영관 번호 (FK)
    private TheaterDTO theaterDTO;
    private ScheduleDTO ScheduleDTO;
    public SeatDTO() {
    }

    public SeatDTO(int seatCode, String seatType, String seatRealNum, BigDecimal sale, int screeningNum, TheaterDTO theaterDTO, ScheduleDTO scheduleDTO) {
        this.seatCode = seatCode;
        this.seatType = seatType;
        this.seatRealNum = seatRealNum;
        this.sale = sale;
        this.screeningNum = screeningNum;
        this.theaterDTO = theaterDTO;
        ScheduleDTO = scheduleDTO;
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

    public TheaterDTO getTheaterDTO() {
        return theaterDTO;
    }

    public void setTheaterDTO(TheaterDTO theaterDTO) {
        this.theaterDTO = theaterDTO;
    }

    public ScheduleDTO getScheduleDTO() {
        return ScheduleDTO;
    }

    public void setScheduleDTO(ScheduleDTO scheduleDTO) {
        ScheduleDTO = scheduleDTO;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "seatCode=" + seatCode +
                ", seatType='" + seatType + '\'' +
                ", seatRealNum='" + seatRealNum + '\'' +
                ", sale=" + sale +
                ", screeningNum=" + screeningNum +
                ", theaterDTO=" + theaterDTO +
                ", ScheduleDTO=" + ScheduleDTO +
                '}';
    }
}
