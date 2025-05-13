package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.HistoriqueAction;
import com.example.demo.models.User;

public interface HistoriqueActionRepository extends JpaRepository<HistoriqueAction, Long> {

    List<HistoriqueAction> findByUtilisateur(User utilisateur);
@Query("SELECT COUNT(h) FROM HistoriqueAction h WHERE DATE(h.date) = :date")
long countByDate(@Param("date") LocalDate date);

}
