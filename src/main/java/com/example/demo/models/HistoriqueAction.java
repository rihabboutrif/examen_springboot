package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistoriqueAction {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String action;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;    // Getters & Setters

	
}