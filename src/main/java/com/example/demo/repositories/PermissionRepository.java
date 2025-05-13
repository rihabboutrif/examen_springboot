package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    boolean existsByNom(String nom);
@Query("SELECT COUNT(p) FROM Permission p")
long countPermissions();

}
