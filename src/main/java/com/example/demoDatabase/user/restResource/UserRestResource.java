package com.example.demoDatabase.user.restResource;

import com.example.demoDatabase.common.util.ResponseUtil;
import com.example.demoDatabase.user.dto.UserDTO;
import com.example.demoDatabase.user.dto.UserDTOForSave;
import com.example.demoDatabase.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public Object register(@RequestBody @Valid UserDTOForSave user) {
        return ResponseUtil.get(userService.save(user), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public Object findAllUser() {
        return ResponseUtil.get(userService.findAll(UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Object deleteUser(@RequestParam UUID id) {
        userService.delete(id);
        return ResponseUtil.get("Successfully deleted", HttpStatus.OK);
    }

}