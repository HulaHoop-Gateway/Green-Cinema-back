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

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*db에서 얻어온 값  전달*/
    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> getUserDTOList() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return ResponseEntity.ok(userDTOList);
    }
}
