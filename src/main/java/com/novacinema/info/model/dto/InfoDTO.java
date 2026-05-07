package com.novacinema.info.model.dto;

// 개별 영화 작품의 메타데이터(제목, 상영 시간, 관람 등급 등)를 담는 데이터 전송 객체
// 데이터베이스의 T_Info 테이블과 매핑되며 상영 일정 구성 시 활용된다
public class InfoDTO {
    // 시스템에서 영화 작품을 식별하는 고유 번호
    private int movieNum;            
    
    // 영화의 공식 제목
    private String movieTitle;       
    
    // 영화의 총 상영 시간 (분 단위)
    private int runningTime;         
    
    // 관람 제한 연령을 나타내는 등급 정보 (예: 12세 관람가, 15세 관람가 등)
    private String audienceRating;   

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
