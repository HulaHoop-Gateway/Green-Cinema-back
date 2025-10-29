package com.novacinema.theater.controller;

import com.novacinema.theater.model.dto.TheaterDTO;
import com.novacinema.theater.model.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/theater")
@CrossOrigin(origins = "http://localhost:5173")
public class TheaterController {
    private  final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }
    @GetMapping("/list")
    public ResponseEntity<List<TheaterDTO>> getSeatDTOList() {
        List<TheaterDTO> theaterDTOList=theaterService.getAlltheaters();
        return ResponseEntity.ok(theaterDTOList);
    }
}