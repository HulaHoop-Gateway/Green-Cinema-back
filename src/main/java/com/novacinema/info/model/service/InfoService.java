package com.novacinema.info.model.service;
import com.novacinema.info.model.dao.InfoMapper;
import com.novacinema.info.model.dto.InfoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    private final InfoMapper infoMapper;

    public InfoService(InfoMapper infoMapper) {
        this.infoMapper = infoMapper;
    }
    public List<InfoDTO> selectAllInfo(){
        return(infoMapper.selectAllInfo());
    }
}
