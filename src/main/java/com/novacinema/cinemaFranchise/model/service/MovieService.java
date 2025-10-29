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
            if ("movie_booking_step1".equals(intent)) {
                // 🎬 Step1: 사용자 주소 기반 가까운 영화관 조회
                String userId = (String) data.get("userId");
                List<Map<String, Object>> nearest = movieMapper.findNearestCinemas(userId);
                result.put("cinemas", nearest);

            } else if ("movie_booking_step2".equals(intent)) {
                // 🎥 Step2: 선택한 영화관의 상영작 목록 (오늘~5일치)
                String branchName = (String) data.get("branchName");

                // ✅ 수정: findNowPlayingAfter → findNowPlaying
                List<Map<String, Object>> movies = movieMapper.findNowPlaying(branchName);
                result.put("movies", movies);

            } else if ("movie_booking_step3".equals(intent)) {
                // 💺 Step3: 영화의 좌석 상태 조회
                String branchName = (String) data.get("branchName");
                String movieTitle = (String) data.get("movieTitle");
                List<Map<String, Object>> seats = movieMapper.findSeatStatus(branchName, movieTitle);
                result.put("seats", seats);

            } else if ("movie_booking_step4".equals(intent)) {
                // 🎫 Step4: 좌석 예약 처리
                String branchName = (String) data.get("branchName");
                String movieTitle = (String) data.get("movieTitle");
                String seat = (String) data.get("seat");
                String memberName = (String) data.get("memberName");

                movieMapper.reserveSeat(memberName, branchName, movieTitle, seat);
                result.put("status", "success");
                result.put("message", seat + " 좌석이 예매되었습니다.");

            } else {
                result.put("error", "Unknown intent: " + intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "DB 처리 중 오류 발생: " + e.getMessage());
        }

        return result;
    }
}
