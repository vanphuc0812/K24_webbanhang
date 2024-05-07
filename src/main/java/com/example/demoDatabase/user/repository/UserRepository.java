package com.example.demoDatabase.user.repository;

import com.example.demoDatabase.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User save(User user);
    public List<User> findAll();

}
