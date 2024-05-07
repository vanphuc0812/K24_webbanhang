package com.example.demoDatabase.user.service;

import com.example.demoDatabase.common.util.WBHMapper;
import com.example.demoDatabase.user.dto.UserDTO;
import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository userRepository ;
    WBHMapper wbhMapper;

    public UserService(UserRepository userRepository, WBHMapper wbhMapper) {
        this.userRepository = userRepository;
        this.wbhMapper = wbhMapper;
    }

    public User save(String username, String password) {
        User user = new User(username, password, 20, null);
        userRepository.save(user);
        return user;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map((user) -> {
            return wbhMapper.map(user, UserDTO.class);
        }).toList();
    }
}
