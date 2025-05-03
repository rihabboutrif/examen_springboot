package com.example.demo.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Role {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> permissions;
    
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;


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