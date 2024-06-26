package com.example.demoDatabase.user.service;

import com.example.demoDatabase.common.service.GenericService;
import com.example.demoDatabase.user.dto.UserDTO;
import com.example.demoDatabase.user.dto.UserDTOForSave;
import com.example.demoDatabase.user.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends GenericService<User, UserDTO, UUID> {
    UserDTO save(UserDTOForSave userDto);

    UserDTO changeAvatar(String username, MultipartFile addAvatar);

    Optional<User> findByUsername(String username);

    UserDTO login(String username, String password);
}
