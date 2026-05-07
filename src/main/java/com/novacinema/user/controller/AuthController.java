package com.novacinema.user.controller;

import com.novacinema.user.model.dto.UserDTO;
import com.novacinema.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 클라이언트의 로그인 요청을 받아 인증을 처리하는 컨트롤러 창구
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    // 요청받은 아이디와 비밀번호로 로그인을 시도하고, 성공 시 사용자 핵심 정보를 맵 형태로 반환한다
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String id = loginData.get("id");
        String password = loginData.get("password");

        UserDTO user = userService.login(id, password);
        if (user != null) {
            return ResponseEntity.ok(Map.of(
                    "message", "로그인 성공",
                    "name", user.getMemberName(),
                    "userCode", user.getMemberCode(),
                    "phoneNumber", user.getPhoneNumber()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인 실패"));
        }
    }
}
