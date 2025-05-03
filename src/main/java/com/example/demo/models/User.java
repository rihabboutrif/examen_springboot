package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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


		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMotDePasse() {
			return motDePasse;
		}

		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}

		public boolean isActif() {
			return actif;
		}

		public void setActif(boolean actif) {
			this.actif = actif;
		}
	
		
		


}
