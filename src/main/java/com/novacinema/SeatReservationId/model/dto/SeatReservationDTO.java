package com.novacinema.SeatReservationId.model.dto;

import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.seat.model.dto.SeatDTO;

import java.sql.Timestamp;

// 특정 상영 일정 내 개별 좌석의 예약(선점) 상태를 관리하는 데이터 전송 객체
// 데이터베이스의 T_SeatReservation 테이블과 매핑되며, 중복 예약을 방지하기 위한 핵심 데이터로 활용된다
public class SeatReservationDTO {
    // 예매 프로세스에서 부여된 고유 예매 ID (외래키 성격)
    private String reservationId;     
    
    // 해당 좌석이 속한 구체적인 상영 일정의 식별 번호
    private int scheduleNum;          
    
    // 예약 대상이 되는 좌석의 고유 식별 코드
    private long seatCode;            
    
    // 좌석의 예약 완료(선점) 여부 (true: 예약됨, false: 예약 가능)
    private boolean reserved;         
    
    // 좌석이 실제로 예약(결제)된 정확한 타임스탬프
    private Timestamp reservedAt;     

    // 해당 좌석이 포함된 상영 일정의 상세 정보를 갖는 연관 객체
    private ScheduleDTO scheduleDTO;
    
    // 해당 좌석의 물리적 배치(행, 열) 및 가격 정보를 갖는 연관 객체
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