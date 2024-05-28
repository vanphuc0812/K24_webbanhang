package com.example.demoDatabase.user.service;

import com.example.demoDatabase.common.exception.WBHBussinessExeption;
import com.example.demoDatabase.common.util.WBHMapper;
import com.example.demoDatabase.security.jwt.JwtUtils;
import com.example.demoDatabase.user.dto.UserDTO;
import com.example.demoDatabase.user.dto.UserDTOForSave;
import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WBHMapper mapper;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, WBHMapper wbhMapper, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = wbhMapper;
        this.jwtUtils = jwtUtils;
    }

    public UserDTO save(UserDTOForSave userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDTO login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new WBHBussinessExeption("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WBHBussinessExeption("Wrong password");
        }
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        userDTO.setToken(jwtUtils.generateToken(username));
        return userDTO;
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
