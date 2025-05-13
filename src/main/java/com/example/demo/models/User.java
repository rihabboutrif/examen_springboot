package com.example.demo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class User {
		
	    @Id
	    @GeneratedValue
		@EqualsAndHashCode.Include
	    private Long id;
	    private String nom;
	    private String email;
	    private String motDePasse;
	    private boolean actif;

	    @ManyToOne
	    @JoinColumn(name = "role_id")
	    private Role role;	    // Getters & Setters
	    
	    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.PERSIST)
		@JsonIgnore
	    private List<HistoriqueAction> historiques;


		
	
		
		


}
