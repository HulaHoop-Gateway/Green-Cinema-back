package com.novacinema.SeatReservationId.model.dto;

import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.seat.model.dto.SeatDTO;

import java.sql.Timestamp;

public class SeatReservationDTO {
    private String reservationId;     // 예매 고유 ID
    private int scheduleNum;          // 상영 일정 번호
    private long seatCode;            // 좌석 코드
    private boolean reserved;         // 예약 여부
    private Timestamp reservedAt;     // 예약 시간


    private ScheduleDTO scheduleDTO;
    private SeatDTO seatDTO;

    public SeatReservationDTO() {
    }

    public SeatReservationDTO(String reservationId, int scheduleNum, long seatCode, boolean reserved, Timestamp reservedAt, ScheduleDTO scheduleDTO, SeatDTO seatDTO) {
        this.reservationId = reservationId;
        this.scheduleNum = scheduleNum;
        this.seatCode = seatCode;
        this.reserved = reserved;
        this.reservedAt = reservedAt;
        this.scheduleDTO = scheduleDTO;
        this.seatDTO = seatDTO;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public long getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(long seatCode) {
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

    public ScheduleDTO getScheduleDTO() {
        return scheduleDTO;
    }

    public void setScheduleDTO(ScheduleDTO scheduleDTO) {
        this.scheduleDTO = scheduleDTO;
    }

    public SeatDTO getSeatDTO() {
        return seatDTO;
    }

    public void setSeatDTO(SeatDTO seatDTO) {
        this.seatDTO = seatDTO;
    }

    @Override
    public String toString() {
        return "SeatReservationDTO{" +
                "reservationId='" + reservationId + '\'' +
                ", scheduleNum=" + scheduleNum +
                ", seatCode=" + seatCode +
                ", reserved=" + reserved +
                ", reservedAt=" + reservedAt +
                ", scheduleDTO=" + scheduleDTO +
                ", seatDTO=" + seatDTO +
                '}';
    }
}