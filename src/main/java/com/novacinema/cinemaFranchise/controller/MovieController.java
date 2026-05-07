package com.novacinema.cinemaFranchise.controller;

import com.novacinema.cinemaFranchise.model.dto.IntentPayLoadDTO;
import com.novacinema.cinemaFranchise.model.service.MovieBookingService;
import com.novacinema.cinemaFranchise.model.service.MovieCancelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    private final MovieBookingService movieService;
    private final MovieCancelService movieCancelService;

    public MovieController(MovieBookingService movieService, MovieCancelService movieCancelService) {
        this.movieService = movieService;
        this.movieCancelService = movieCancelService;
    }

    // 챗봇 등 외부로부터 전달된 영화 관련 의도(Intent) 데이터를 파싱하여 적절한 서비스 로직으로 분기한다
    @PostMapping("/dispatch")
    public ResponseEntity<Map<String, Object>> handleIntent(@RequestBody IntentPayLoadDTO payload) {
        String intent = payload.getIntent();
        Map<String, Object> data = payload.getData();

        log.info("[MovieController] intent: {}", intent);
        log.info("[MovieController] data: {}", data);

        Map<String, Object> result;

        if (intent != null && intent.startsWith("movie_cancel")) {
            result = movieCancelService.processIntent(intent, data);
        } else {
            result = movieService.processIntent(intent, data);
        }

        return ResponseEntity.ok(result);
    }
}
