package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.cinemaFranchise.model.dao.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public Map<String, Object> processIntent(String intent, Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();

        try {
            switch (intent) {

                // ✅ Step1: 가까운 영화관 조회
                case "movie_booking_step1": {
                    List<Map<String, Object>> nearest = movieMapper.findNearestCinemas();
                    result.put("cinemas", nearest);
                    break;
                }

                // ✅ Step2: 특정 지점 상영 영화 조회
                case "movie_booking_step2": {
                    String branchName = (String) data.get("branchName");
                    List<Map<String, Object>> movies = movieMapper.findNowPlaying(branchName);
                    result.put("movies", movies);
                    break;
                }

                // ✅ Step3: 상영 시간 선택 후 해당 회차 좌석 조회
                case "movie_booking_step3": {
                    Integer scheduleNum = (Integer) data.get("scheduleNum");
                    List<Map<String, Object>> seats = movieMapper.findSeatStatus(scheduleNum);
                    result.put("seats", seats);
                    break;
                }

                // ✅ Step4: 좌석 선택 → HOLD 상태 예약
                case "movie_booking_step4": {
                    Integer scheduleNum = (Integer) data.get("scheduleNum");
                    Integer seatCode = (Integer) data.get("seatCode");
                    String memberName = (String) data.get("memberName");

                    movieMapper.reserveSeat(scheduleNum, seatCode, memberName);

                    result.put("status", "success");
                    result.put("message", "좌석이 임시예약(HOLD)되었습니다. 10분 내 결제하세요.");
                    break;
                }

                default:
                    result.put("error", "Unknown intent: " + intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "DB 처리 중 오류: " + e.getMessage());
        }

        return result;
    }
}
