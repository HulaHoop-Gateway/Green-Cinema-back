package com.novacinema.cancellation.controller;

import com.novacinema.cinemaFranchise.model.service.MovieCancelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cinema")
public class CinemaCancellationController {

    private final MovieCancelService movieCancelService;

    public CinemaCancellationController(MovieCancelService movieCancelService) {
        this.movieCancelService = movieCancelService;
    }

    @PutMapping("/cancel")
    public ResponseEntity<Map<String, Object>> cancelReservation(@RequestBody Map<String, Object> request) {
        Map<String, Object> result = new HashMap<>();

        try {
            Long transactionNum = null;
            if (request.get("transactionNum") instanceof Integer) {
                transactionNum = ((Integer) request.get("transactionNum")).longValue();
            } else if (request.get("transactionNum") instanceof Long) {
                transactionNum = (Long) request.get("transactionNum");
            }

            String memberCode = (String) request.get("memberCode");

            if (transactionNum == null || memberCode == null) {
                result.put("success", false);
                result.put("message", "필수 정보가 누락되었습니다.");
                return ResponseEntity.badRequest().body(result);
            }

            // MovieCancelService의 기존 로직 재사용
            // step3: 실제 취소 처리 + Admin 서버로 취소 트랜잭션 전송
            Map<String, Object> data = new HashMap<>();
            data.put("reservationNum", String.valueOf(transactionNum)); // fallback
            data.put("transactionNum", transactionNum);

            Map<String, Object> cancelResult = movieCancelService.processIntent("movie_cancel_step3", data);

            if (cancelResult.containsKey("error")) {
                result.put("success", false);
                result.put("message", cancelResult.get("error"));
                return ResponseEntity.badRequest().body(result);
            }

            result.put("success", true);
            result.put("message", cancelResult.get("message"));
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "취소 처리 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }
}
