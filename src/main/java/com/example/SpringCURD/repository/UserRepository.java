package com.example.SpringCURD.repository;

import com.example.SpringCURD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
