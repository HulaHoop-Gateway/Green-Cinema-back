package com.ohgiraffers.com.cinemaFranchise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemafranchise")
public class CinemaFranchiseController {
    private final CinemaFranchiseService cinemaFranchiseService;
    public CinemaFranchiseController(CinemaFranchiseService cinemaFranchiseService){
        this.cinemaFranchiseService=cinemaFranchiseService;
    }
    @PostMapping("/list")
    public ResponseEntity<String> receiveJson(@RequestBody CinemaFranchiseDTO cinemaFranchiseDTO) {

        System.out.println("영화관 기본키: " + cinemaFranchiseDTO.getBranchNum());
        System.out.println("영화관 이름: " + cinemaFranchiseDTO.getBranchName());
        System.out.println("영화관 주소: " + cinemaFranchiseDTO.getAddress());

        return ResponseEntity.ok("JSON 수신 완료: " + cinemaFranchiseDTO.getBranchName());
    }
    @GetMapping("/list")
    public ResponseEntity<List<CinemaFranchiseDTO>> getCinemaFranchiseList() {
        List<CinemaFranchiseDTO> franchiseList = cinemaFranchiseService.getAllCinemaFranchises();
        return ResponseEntity.ok(franchiseList);
    }

}
