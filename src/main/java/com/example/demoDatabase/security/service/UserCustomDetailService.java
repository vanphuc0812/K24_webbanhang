package com.example.demoDatabase.security.service;

import com.example.demoDatabase.common.exception.WBHBussinessExeption;
import com.example.demoDatabase.security.model.UserDetailsImpl;
import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserCustomDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserCustomDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new WBHBussinessExeption("User not found"));

        return UserDetailsImpl.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(
                        // admin,user,... -> [WBH_USER,WBH_ADMIN)
//                        List.of(new SimpleGrantedAuthority("WBH_USER"))
                        // a,b,c,d => split(",") -> [a,b,c,d]
                        // a/b/c/d => split("/") -> [a,b,c,d]
                        Arrays.stream(user.getRoles().split(",")).map(role -> {
                            return new SimpleGrantedAuthority("WBH_" + role.trim().toUpperCase());
                        }).toList()
                )
                .build();
    }
}
