package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Role;
import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    public  Role getRoleById(Long id);

    @Query("SELECT COUNT(u) FROM User u")
long countUsers();

}