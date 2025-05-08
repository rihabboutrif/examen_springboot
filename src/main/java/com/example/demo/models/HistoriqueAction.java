package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data

public class HistoriqueAction {
    @Id @GeneratedValue
    private Long id;
    private String action;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User utilisateur;    // Getters & Setters

	
}