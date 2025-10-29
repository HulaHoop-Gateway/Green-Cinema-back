package com.novacinema.user.model.dao;

import com.novacinema.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO findById(String id);
}
