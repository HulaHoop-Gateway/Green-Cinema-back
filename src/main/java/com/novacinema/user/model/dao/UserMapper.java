package com.novacinema.user.model.dao;

import com.novacinema.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO findById(String id);

    List<UserDTO> selectAllUsers();

    UserDTO findByPhoneNumber(String phoneNumber);
}
