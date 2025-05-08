package com.example.demo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.HistoriqueAction;
import com.example.demo.models.User;
import com.example.demo.repositories.HistoriqueActionRepository;

@Service
public class LogService {
    @Autowired private HistoriqueActionRepository historiqueRepo;

    public void log(User user, String message) {
        HistoriqueAction h = new HistoriqueAction();
        h.setUtilisateur(user);
        h.setAction(message);
        h.setDate(LocalDateTime.now());
        historiqueRepo.save(h);
    }
}