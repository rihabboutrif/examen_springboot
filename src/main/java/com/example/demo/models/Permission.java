package com.example.demo.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
@Entity
@Data

public class Permission {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String description;
    
    
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
    
    
   
}