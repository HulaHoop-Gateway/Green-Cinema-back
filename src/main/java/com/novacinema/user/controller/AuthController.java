package com.novacinema.user.controller;

import com.novacinema.user.model.dto.UserDTO;
import com.novacinema.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

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
