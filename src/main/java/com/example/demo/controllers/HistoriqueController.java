package com.example.demo.controllers;

import com.example.demo.models.HistoriqueAction;
import com.example.demo.repositories.HistoriqueActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historique")
@CrossOrigin(origins = "*") // autoriser CORS pour Angular
public class HistoriqueController {

    @Autowired
    private HistoriqueActionRepository historiqueRepo;

    @GetMapping
    public List<HistoriqueAction> getAll() {
        return historiqueRepo.findAll();
    }
}
