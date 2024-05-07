package com.example.demoDatabase.user.model;

import com.example.demoDatabase.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class User extends BaseEntity {
    @Column(name = UserEntity.USERNAME)
    private String username;
    @Column(name = UserEntity.PASSWORD)
    private String password;
    @Column(name = UserEntity.AGE)
    private int age;
    @Column(name = UserEntity.GENDER)
    private String gender;
}

