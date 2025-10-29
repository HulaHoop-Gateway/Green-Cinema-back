package com.novacinema.info.model.dto;

public class InfoDTO {
    private int movieNum;            // 영화 고유 번호
    private String movieTitle;       // 영화 제목
    private int runningTime;         // 상영 시간 (분 단위)
    private String audienceRating;   // 관람 등급

    // 기본 생성자
    public InfoDTO() {}

    public InfoDTO(int movieNum, String movieTitle, int runningTime, String audienceRating) {
        this.movieNum = movieNum;
        this.movieTitle = movieTitle;
        this.runningTime = runningTime;
        this.audienceRating = audienceRating;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getAudienceRating() {
        return audienceRating;
    }

    public void setAudienceRating(String audienceRating) {
        this.audienceRating = audienceRating;
    }

    @Override
    public String toString() {
        return "InfoDTO{" +
                "movieNum=" + movieNum +
                ", movieTitle='" + movieTitle + '\'' +
                ", runningTime=" + runningTime +
                ", audienceRating='" + audienceRating + '\'' +
                '}';
    }
}
