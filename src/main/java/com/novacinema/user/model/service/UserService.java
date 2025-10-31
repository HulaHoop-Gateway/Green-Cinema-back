package com.novacinema.user.model.service;


import com.novacinema.user.model.dao.UserMapper;
import com.novacinema.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserDTO login(String id, String password) {
        UserDTO user = userMapper.findById(id);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<UserDTO> getAllUsers() {
        return userMapper.selectAllUsers();
    }
}
