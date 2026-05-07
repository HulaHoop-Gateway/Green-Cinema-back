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
    // 영화관 지점 목록 조회 API 메인 진입점
    // DB에 저장된 전체 지점 데이터를 DTO 리스트 형태로 반환한다
    @GetMapping("/list")
    public ResponseEntity<List<CinemaFranchiseDTO>> getCinemaFranchiseList() {
        List<CinemaFranchiseDTO> franchiseList = cinemaFranchiseService.getAllCinemaFranchises();
        return ResponseEntity.ok(franchiseList);
    }
    // 폼 데이터 방식(x-www-form-urlencoded)으로 지점 정보를 수신하여 확인하는 테스트용 엔드포인트
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
    // 클라이언트로부터 JSON 형식의 지점 정보를 전달받아 데이터 매핑을 확인하는 엔드포인트
    @PostMapping("/list2")
    public ResponseEntity<CinemaFranchiseDTO> receiveCinemaData(@RequestBody CinemaFranchiseDTO cinemaFranchiseDTO) {
        System.out.println("8082지점 번호: " + cinemaFranchiseDTO.getBranchNum());
        System.out.println("지점 이름: " + cinemaFranchiseDTO.getBranchName());
        System.out.println("주소: " + cinemaFranchiseDTO.getAddress());

        return ResponseEntity.ok(cinemaFranchiseDTO
        );
    }
    // 전체 영화관 지점 목록을 조회하고, 콘솔에 조회된 지점 수를 로깅한 뒤 반환한다
    @GetMapping("/list3")
    public ResponseEntity<List<CinemaFranchiseDTO>> getCinemaFranchiseList3() {
        List<CinemaFranchiseDTO> franchiseList = cinemaFranchiseService.getAllCinemaFranchises();
        System.out.println("조회된 지점 수: " + franchiseList.size());

        return ResponseEntity.ok(franchiseList);
    }
    // 신규 영화관 지점 정보를 JSON 형태로 전달받아 서비스 계층을 통해 DB에 등록한다
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
