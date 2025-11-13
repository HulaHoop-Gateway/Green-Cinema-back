package com.novacinema.schedule.model.dto;

import com.novacinema.info.model.dto.InfoDTO;
import com.novacinema.theater.model.dto.TheaterDTO;

import java.time.LocalDateTime;

public class ScheduleDTO {
    private int scheduleNum;
    private LocalDateTime screeningDate; // ✅ 변경됨
    private int screeningNum;
    private int movieNum;

    private InfoDTO movieInfo;
    private TheaterDTO theaterInfo;

    public ScheduleDTO() {
    }

    public ScheduleDTO(int scheduleNum, LocalDateTime screeningDate, int screeningNum, int movieNum, InfoDTO movieInfo, TheaterDTO theaterInfo) {
        this.scheduleNum = scheduleNum;
        this.screeningDate = screeningDate;
        this.screeningNum = screeningNum;
        this.movieNum = movieNum;
        this.movieInfo = movieInfo;
        this.theaterInfo = theaterInfo;
    }

    // ✅ 예매 취소 가능 여부 판단
    public boolean isCancelable() {
        return screeningDate.isAfter(LocalDateTime.now().minusMinutes(1));
    }


    public int getScheduleNum() {
        return scheduleNum;
    }

    public void setScheduleNum(int scheduleNum) {
        this.scheduleNum = scheduleNum;
    }

    public LocalDateTime getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(LocalDateTime screeningDate) {
        this.screeningDate = screeningDate;
    }

    public int getScreeningNum() {
        return screeningNum;
    }

    public void setScreeningNum(int screeningNum) {
        this.screeningNum = screeningNum;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }

    public InfoDTO getMovieInfo() {
        return movieInfo;
    }

    public void setMovieInfo(InfoDTO movieInfo) {
        this.movieInfo = movieInfo;
    }

    public TheaterDTO getTheaterInfo() {
        return theaterInfo;
    }

    public void setTheaterInfo(TheaterDTO theaterInfo) {
        this.theaterInfo = theaterInfo;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "scheduleNum=" + scheduleNum +
                ", screeningDate=" + screeningDate +
                ", screeningNum=" + screeningNum +
                ", movieNum=" + movieNum +
                ", movieInfo=" + movieInfo +
                ", theaterInfo=" + theaterInfo +
                '}';
    }
}
