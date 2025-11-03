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


    //Movie 서버가 DB 처리 후 결과 반환
    @PostMapping("/dispatch")
    public ResponseEntity<Map<String, Object>> handleIntent(@RequestBody Map<String, Object> payload) {
        String intent = (String) payload.get("intent");
        Map<String, Object> data = (Map<String, Object>) payload.get("data");

        Map<String, Object> result = movieService.processIntent(intent, data);
        return ResponseEntity.ok(result);
    }
}
