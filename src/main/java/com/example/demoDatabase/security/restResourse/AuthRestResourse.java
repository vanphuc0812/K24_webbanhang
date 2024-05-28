package com.example.demoDatabase.security.restResourse;

import com.example.demoDatabase.common.util.ResponseUtil;
import com.example.demoDatabase.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthRestResourse {
    private final UserService userService;

    public AuthRestResourse(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @PreAuthorize("ROLE")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ResponseUtil.get(userService.login(username, password), HttpStatus.OK);
    }
}
