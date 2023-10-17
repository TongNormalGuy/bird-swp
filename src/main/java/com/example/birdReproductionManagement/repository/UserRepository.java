package com.example.birdReproductionManagement.repository;

import com.example.birdReproductionManagement.entity.Role;
import com.example.birdReproductionManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    List<User> findByRole(Role role);
    @Query("SELECT a FROM User a WHERE a.role = :role")
    List<User> findAllByRole(Role role);
}
