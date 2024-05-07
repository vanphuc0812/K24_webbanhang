package com.example.demoDatabase.user.repository;

import com.example.demoDatabase.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User save(User user);
    public List<User> findAll();
    public User findById(UUID id);
    public void deleteById(UUID id);

}
