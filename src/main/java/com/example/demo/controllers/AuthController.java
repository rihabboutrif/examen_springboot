package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.MyUserDetails;
import com.example.demo.services.UserDetailsServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired private AuthenticationManager authenticationManager;

    AuthController(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }
    @PostConstruct
public void init() {
    System.out.println("AuthController initialisé ✅");
}


    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpServletRequest httpRequest) {
    try {
        String email = credentials.get("email");
        String password = credentials.get("motDePasse");
System.out.println("Email reçu : " + email);
System.out.println("Mot de passe reçu : " + password);

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    httpRequest.getSession(true); // Force session creation

     // Get user details from authentication
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal(); // Adjust to your UserDetails class
        User user = userDetails.getUser(); // Assuming you have a getUser() method

        // Return user info
        return ResponseEntity.ok(Map.of(
            "message", "Connexion réussie",
            "user", Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "role", user.getRole().getNom(),
                "permissions", user.getRole().getPermissions() // list of permission names or objects
            )
        ));
        // Renvoie un objet JSON avec un message de succès
    } catch (Exception e) {
        e.printStackTrace(); // pour voir l’erreur exacte dans la console

        // Renvoie un objet JSON avec un message d'erreur
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Email ou mot de passe incorrect"));
    }
}

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Déconnexion réussie");
    }
}