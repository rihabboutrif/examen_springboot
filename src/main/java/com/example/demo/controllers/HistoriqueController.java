package com.example.demo.controllers;
import org.springframework.security.core.Authentication;

import com.example.demo.models.HistoriqueAction;
import com.example.demo.models.User;
import com.example.demo.repositories.HistoriqueActionRepository;
import com.example.demo.services.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/historique")
@CrossOrigin(origins = "*") // autoriser CORS pour Angular
public class HistoriqueController {

    @Autowired
    private HistoriqueActionRepository historiqueRepo;

    @Autowired
    private LogService logService;
    @GetMapping
    public List<HistoriqueAction> getAll() {
        return historiqueRepo.findAll();
    }

    @GetMapping("/me")
public List<HistoriqueAction> getMyActions(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    return historiqueRepo.findByUtilisateur(user);
}
 @GetMapping("/last-7-days")
    public List<Map<String, Object>> getActionsLast7Days() {
        return logService.getActionCountsLast7Days(); // ✅ appel sur l'instance injectée
    }

}
