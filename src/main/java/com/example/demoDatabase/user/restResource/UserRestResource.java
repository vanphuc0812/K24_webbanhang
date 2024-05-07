package com.example.demoDatabase.user.restResource;

import com.example.demoDatabase.user.dto.UserDTOForSave;
import com.example.demoDatabase.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController   // = @Controller + @ResponseBody
@RequestMapping("/user")
public class UserRestResource {
    UserService userService;

    public UserRestResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Object register(@RequestBody UserDTOForSave user) {
        return userService.save(user);
//        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public Object findAllUser() {
        return userService.findAll();
    }
    //1. Find User by username
    //2. Delete user by ID

    @DeleteMapping("/delete")
    public Object deleteUser(@RequestParam UUID id) {
        userService.delete(id);
        return "Successfully deleted";
    }

}