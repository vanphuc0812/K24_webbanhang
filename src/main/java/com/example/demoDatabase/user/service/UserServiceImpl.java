package com.example.demoDatabase.user.service;

import com.example.demoDatabase.common.util.WBHMapper;
import com.example.demoDatabase.user.dto.UserDTO;
import com.example.demoDatabase.user.dto.UserDTOForSave;
import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    WBHMapper mapper;

    public UserServiceImpl(UserRepository userRepository, WBHMapper wbhMapper) {
        this.userRepository = userRepository;
        this.mapper = wbhMapper;
    }

    public UserDTO save(UserDTOForSave userDto) {
        User user = mapper.map(userDto, User.class);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public WBHMapper getMapper() {
        return mapper;
    }
}
