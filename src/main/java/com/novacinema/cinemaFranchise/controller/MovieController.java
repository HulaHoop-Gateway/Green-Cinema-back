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

    // 🎬 Movie intent 처리
    @PostMapping("/dispatch")
    public ResponseEntity<Map<String, Object>> handleIntent(@RequestBody IntentPayLoadDTO payload) {
        String intent = payload.getIntent();
        Map<String, Object> data = payload.getData();

        log.info("🎬 [MovieController] intent: {}", intent);
        log.info("🎬 [MovieController] data: {}", data);

        Map<String, Object> result;

        if (intent != null && intent.startsWith("movie_cancel")) {
            result = movieCancelService.processIntent(intent, data);
        } else {
            result = movieService.processIntent(intent, data);
        }

        return ResponseEntity.ok(result);
    }
}
