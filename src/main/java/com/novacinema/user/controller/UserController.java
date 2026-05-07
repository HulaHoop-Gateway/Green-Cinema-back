package com.novacinema.user.controller;

import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.seat.model.service.SeatService;
import com.novacinema.user.model.dto.UserDTO;
import com.novacinema.user.model.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 회원(사용자) 정보 조회 요청을 처리하는 컨트롤러 창구
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 데이터베이스에 등록된 전체 사용자 목록을 조회하여 클라이언트에 반환한다
    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUserDTOList() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return ResponseEntity.ok(userDTOList);
    }
}
