package com.example.demoDatabase.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOForSave {
    private String username;
    private String password;
    private int age;
    private String gender;
}
