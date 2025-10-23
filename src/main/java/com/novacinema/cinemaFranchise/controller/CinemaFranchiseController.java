package com.novacinema.cinemaFranchise.controller;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.service.CinemaFranchiseService;
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
    public ResponseEntity<String> receiveForm(CinemaFranchiseDTO cinemaFranchiseDTO) {
        System.out.println("지점 번호: " + cinemaFranchiseDTO.getBranchNum());
        System.out.println("지점 이름: " + cinemaFranchiseDTO.getBranchName());
        System.out.println("주소: " + cinemaFranchiseDTO.getAddress());

        return ResponseEntity.ok(
                        "지점 번호: " + cinemaFranchiseDTO.getBranchNum() + "\n" +
                        "지점 이름: " + cinemaFranchiseDTO.getBranchName() + "\n" +
                        "주소: " + cinemaFranchiseDTO.getAddress()
        );

    }

    @GetMapping("/list")
    public ResponseEntity<List<CinemaFranchiseDTO>> getCinemaFranchiseList() {
        List<CinemaFranchiseDTO> franchiseList = cinemaFranchiseService.getAllCinemaFranchises();
        return ResponseEntity.ok(franchiseList);
    }

}
