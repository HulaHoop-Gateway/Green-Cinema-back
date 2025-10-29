package com.novacinema.cinemaFranchise.controller;

import com.novacinema.cinemaFranchise.model.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // 🎯 게이트웨이(8080)에서 들어오는 intent 처리
    @PostMapping("/dispatch")
    public ResponseEntity<Map<String, Object>> handleIntent(@RequestBody Map<String, Object> payload) {
        String intent = (String) payload.get("intent");
        Map<String, Object> data = (Map<String, Object>) payload.get("data");

        Map<String, Object> result = movieService.processIntent(intent, data);
        return ResponseEntity.ok(result);
    }
}
