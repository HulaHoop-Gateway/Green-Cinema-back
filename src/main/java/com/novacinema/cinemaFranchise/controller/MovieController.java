package com.novacinema.cinemaFranchise.controller;

import com.novacinema.cinemaFranchise.model.dto.IntentPayLoadDTO;
import com.novacinema.cinemaFranchise.model.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    //Movie 서버가 DB 처리 후 결과 반환
    @PostMapping("/dispatch")
    public ResponseEntity<Map<String, Object>> handleIntent(@RequestBody IntentPayLoadDTO payload ) {
        String intent = payload.getIntent();
        Map<String, Object> data = payload.getData();

        log.info("🎬 [MovieController] intent: {}", intent);
        log.info("🎬 [MovieController] data: {}", data);

        Map<String, Object> result = movieService.processIntent(intent, data);
        return ResponseEntity.ok(result);
    }
}
