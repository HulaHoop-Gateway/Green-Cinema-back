package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.cinemaFranchise.model.dao.MovieMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {

    private final MovieMapper movieMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    public Map<String, Object> processIntent(String intent, Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();

        try {
            switch (intent) {

                case "movie_booking_step1": {
                    List<Map<String, Object>> nearest = movieMapper.findNearestCinemas();
                    result.put("cinemas", nearest);
                    break;
                }

                case "movie_booking_step2": {
                    String branchName = (String) data.get("branchName");
                    List<Map<String, Object>> movies = movieMapper.findNowPlaying(branchName);
                    result.put("movies", movies);
                    break;
                }

                case "movie_booking_step3": {
                    Integer scheduleNum = (Integer) data.get("scheduleNum");
                    List<Map<String, Object>> seats = movieMapper.findSeatStatus(scheduleNum);
                    result.put("seats", seats);
                    break;
                }

                // ✅ 좌석 HOLD 예약 + Gateway에 브로드캐스트 요청
                case "movie_booking_step4": {
                    Integer scheduleNum = (Integer) data.get("scheduleNum");
                    Integer seatCode = (Integer) data.get("seatCode");

                    movieMapper.reserveSeat(scheduleNum, seatCode);

                    // ✅ Gateway에 알림 전송
                    Map<String, Object> payload = Map.of("scheduleNum", scheduleNum);
                    try {
                        restTemplate.postForObject(
                                "http://localhost:8080/internal/seat-updated",
                                payload,
                                Void.class
                        );
                        System.out.println("✅ Gateway에 좌석 업데이트 알림 전송");
                    } catch (Exception ex) {
                        System.out.println("⚠️ Gateway 통신 실패: " + ex.getMessage());
                    }

                    result.put("status", "success");
                    result.put("message", "좌석 HOLD 완료 (10분 내 결제)");
                    break;
                }

                default:
                    result.put("error", "Unknown intent: " + intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "DB 오류 (이미 예약된 좌석일 수 있음)");
        }

        return result;
    }
}
