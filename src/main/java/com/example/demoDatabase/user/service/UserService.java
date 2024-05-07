package com.example.demoDatabase.user.service;

import com.example.demoDatabase.common.util.WBHMapper;
import com.example.demoDatabase.user.dto.UserDTO;
import com.example.demoDatabase.user.dto.UserDTOForSave;
import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    UserRepository userRepository;
    WBHMapper wbhMapper;

    public UserService(UserRepository userRepository, WBHMapper wbhMapper) {
        this.userRepository = userRepository;
        this.wbhMapper = wbhMapper;
    }

    public User save(UserDTOForSave userDto) {
        User user = wbhMapper.map(userDto, User.class);
        userRepository.save(user);
        return user;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map((user) -> {
            return wbhMapper.map(user, UserDTO.class);
        }).toList();
    }

    public void delete(UUID id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.deleteById(id);
        }
    }
}
