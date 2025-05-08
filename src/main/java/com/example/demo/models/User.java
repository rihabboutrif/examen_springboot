package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class User {
		
	    @Id
	    @GeneratedValue
	    private Long id;
	    private String nom;
	    private String email;
	    private String motDePasse;
	    private boolean actif;

	    @ManyToOne
	    @JoinColumn(name = "role_id")
	    private Role role;	    // Getters & Setters
	    
	    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.PERSIST)
	    private List<HistoriqueAction> historiques;


		
	
		
		


}
