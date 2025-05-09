package com.example.demo.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Role;
import com.example.demo.models.User;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByNom(String nom);

    Optional<User> findById(Role role);



}
