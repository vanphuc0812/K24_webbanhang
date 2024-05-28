package com.example.demoDatabase.user.dto;

import com.example.demoDatabase.validation.anotation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOForSave {
    @Size(min = 5, max = 50)
    @NotBlank
    @NotEmpty
    @NotNull
    @UniqueUsername
    private String username;
    @Size(min = 5, max = 50)
    @NotBlank
    private String password;
    private int age;
    private String gender;
    private String roles;
}
