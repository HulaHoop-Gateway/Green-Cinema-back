package com.novacinema.SeatReservationId.model.dto;

import com.novacinema.seat.model.dto.SeatDTO;

import java.sql.Timestamp;

public class SeatReservationDTO {
    private int reservationId;     // 예매 고유 ID
    private int scheduleNum;       // 상영 일정 번호
    private int seatCode;          // 좌석 코드
    private boolean reserved;      // 예약 여부
    private Timestamp reservedAt;  // 예약 시간

    private SeatDTO seatInfo;

    public SeatReservationDTO(){}

    public SeatReservationDTO(int reservationId, int scheduleNum, int seatCode, boolean reserved, Timestamp reservedAt, SeatDTO seatInfo) {
        this.reservationId = reservationId;
        this.scheduleNum = scheduleNum;
        this.seatCode = seatCode;
        this.reserved = reserved;
        this.reservedAt = reservedAt;
        this.seatInfo = seatInfo;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public int getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(int seatCode) {
        this.seatCode = seatCode;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Timestamp getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(Timestamp reservedAt) {
        this.reservedAt = reservedAt;
    }

    public SeatDTO getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(SeatDTO seatInfo) {
        this.seatInfo = seatInfo;
    }

    @Override
    public String toString() {
        return "SeatReservationDTO{" +
                "reservationId=" + reservationId +
                ", scheduleNum=" + scheduleNum +
                ", seatCode=" + seatCode +
                ", reserved=" + reserved +
                ", reservedAt=" + reservedAt +
                ", seatInfo=" + seatInfo +
                '}';
    }
}
