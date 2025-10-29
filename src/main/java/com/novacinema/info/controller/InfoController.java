package com.novacinema.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.novacinema.info.model.service.InfoService;
import java.util.List;
import com.novacinema.info.model.dto.InfoDTO;

@RestController
@RequestMapping("/info")
@CrossOrigin(origins = "http://localhost:5173")
public class InfoController {
    private final InfoService InfoService;
    @Autowired

    public InfoController(InfoService infoService) {
        InfoService = infoService;
    }
    @GetMapping("/list")
    public ResponseEntity<List<InfoDTO>> getInfoList(){
        List<InfoDTO> ListInfoDTO=InfoService.selectAllInfo();
        return ResponseEntity.ok(ListInfoDTO);
    }
}
