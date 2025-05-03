package com.example.demo.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
@Entity
public class Permission {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String description;
    
    
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
    
    
    // Getters & Setters
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}