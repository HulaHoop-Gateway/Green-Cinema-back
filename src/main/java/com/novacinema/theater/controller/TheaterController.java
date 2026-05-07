package com.novacinema.theater.controller;

import com.novacinema.theater.model.dto.TheaterDTO;
import com.novacinema.theater.model.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// 상영관 정보 조회 요청을 처리하는 컨트롤러 창구
@RestController
@RequestMapping("/theater")
@CrossOrigin(origins = "http://localhost:5173")
public class TheaterController {
    private  final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }
    // 데이터베이스에 저장된 전체 상영관(Theater) 정보를 조회하여 반환한다
    @GetMapping("/list")
    public ResponseEntity<List<TheaterDTO>> getSeatDTOList() {
        List<TheaterDTO> theaterDTOList=theaterService.getAlltheaters();
        return ResponseEntity.ok(theaterDTOList);
    }
}