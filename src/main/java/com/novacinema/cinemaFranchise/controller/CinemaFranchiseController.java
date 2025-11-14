package com.novacinema.cinemaFranchise.controller;

import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.cinemaFranchise.model.service.CinemaFranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemafranchise")
@CrossOrigin(origins = "http://localhost:5173")
public class CinemaFranchiseController {
    private final CinemaFranchiseService cinemaFranchiseService;
    public CinemaFranchiseController(CinemaFranchiseService cinemaFranchiseService){
        this.cinemaFranchiseService=cinemaFranchiseService;
    }
    /*db에서 얻어온 값  전달*/
    @GetMapping("/list")
    public ResponseEntity<List<CinemaFranchiseDTO>> getCinemaFranchiseList() {
        List<CinemaFranchiseDTO> franchiseList = cinemaFranchiseService.getAllCinemaFranchises();
        return ResponseEntity.ok(franchiseList);
    }
    /*post방식으로 수신*/
    @PostMapping("/list1")
    public ResponseEntity<String> receiveForm(CinemaFranchiseDTO cinemaFranchiseDTO) {
        System.out.println("8082지점 번호: " + cinemaFranchiseDTO.getBranchNum());
        System.out.println("지점 이름: " + cinemaFranchiseDTO.getBranchName());
        System.out.println("주소: " + cinemaFranchiseDTO.getAddress());

        return ResponseEntity.ok(
                        "지점 번호: " + cinemaFranchiseDTO.getBranchNum() + "\n" +
                        "지점 이름: " + cinemaFranchiseDTO.getBranchName() + "\n" +
                        "주소: " + cinemaFranchiseDTO.getAddress()
        );
    }
    /*json방식으로 수신*/
    @PostMapping("/list2")
    public ResponseEntity<CinemaFranchiseDTO> receiveCinemaData(@RequestBody CinemaFranchiseDTO cinemaFranchiseDTO) {
        System.out.println("8082지점 번호: " + cinemaFranchiseDTO.getBranchNum());
        System.out.println("지점 이름: " + cinemaFranchiseDTO.getBranchName());
        System.out.println("주소: " + cinemaFranchiseDTO.getAddress());

        return ResponseEntity.ok(cinemaFranchiseDTO
        );
    }
    /*db에서 얻어온 값  전달*/
    @GetMapping("/list3")
    public ResponseEntity<List<CinemaFranchiseDTO>> getCinemaFranchiseList3() {
        List<CinemaFranchiseDTO> franchiseList = cinemaFranchiseService.getAllCinemaFranchises();
        System.out.println("조회된 지점 수: " + franchiseList.size());

        return ResponseEntity.ok(franchiseList);
    }
    /*json방식으로 수신후 DB추가*/
    @PostMapping("/add")
    public ResponseEntity<CinemaFranchiseDTO> receiveCinemaData4(@RequestBody CinemaFranchiseDTO cinemaFranchiseDTO) {
        System.out.println("8082지점 번호: " + cinemaFranchiseDTO.getBranchNum());
        System.out.println("지점 이름: " + cinemaFranchiseDTO.getBranchName());
        System.out.println("주소: " + cinemaFranchiseDTO.getAddress());
        cinemaFranchiseService.addCinemaFranchise(cinemaFranchiseDTO);
        return ResponseEntity.ok(cinemaFranchiseDTO
        );
    }
    @DeleteMapping("/delete/{branchNum}")
    public ResponseEntity<String> deleteCinemaFranchise(@PathVariable int branchNum) {
        System.out.println("삭제 요청 지점 번호: " + branchNum);
        cinemaFranchiseService.deleteByBranchNum(branchNum);
        return ResponseEntity.ok("지점 번호 " + branchNum + " 삭제 완료");
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateCinemaFranchise(@RequestBody CinemaFranchiseDTO dto) {
        cinemaFranchiseService.updateCinemaFranchise(dto);
        return ResponseEntity.ok("지점 번호 " + dto.getBranchNum() + " 수정 완료");
    }

}
