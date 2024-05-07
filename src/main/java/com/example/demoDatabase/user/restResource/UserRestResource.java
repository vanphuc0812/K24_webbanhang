package com.example.demoDatabase.user.restResource;

import com.example.demoDatabase.user.model.User;
import com.example.demoDatabase.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController   // = @Controller + @ResponseBody
@RequestMapping("/user")
public class UserRestResource {
    UserService userService;
    public UserRestResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Object register(@RequestParam String username, @RequestParam String password) {
        return userService.save(username, password);
//        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public Object findAllUser() {
        return userService.findAll();
    }
}