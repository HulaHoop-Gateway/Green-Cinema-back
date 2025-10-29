package com.novacinema.info.model.dao;


import com.novacinema.info.model.dto.InfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoMapper {
    List<InfoDTO> selectAllInfo();
}
