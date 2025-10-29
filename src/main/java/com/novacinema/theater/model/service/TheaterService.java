package com.novacinema.theater.model.service;



import com.novacinema.seat.model.dto.SeatDTO;
import com.novacinema.theater.model.dao.TheaterMapper;
import com.novacinema.theater.model.dto.TheaterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    private final TheaterMapper theaterMapper;

    public TheaterService(TheaterMapper theaterMapper) {
        this.theaterMapper = theaterMapper;
    }

    public List<TheaterDTO> getAlltheaters() {
        return theaterMapper.selectAllTheaters();
    }
}

