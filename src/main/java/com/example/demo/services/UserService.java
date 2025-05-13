package com.example.demo.services;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.domain.Pageable;
@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private LogService logService;
    @Autowired private RoleRepository roleRepository;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User createUser(User user) {
    if (user.getMotDePasse() == null || user.getMotDePasse().isEmpty()) {
        throw new IllegalArgumentException("Le mot de passe ne peut pas être vide");
    }

    // Encoder le mot de passe
    user.setMotDePasse(new BCryptPasswordEncoder().encode(user.getMotDePasse()));

    user.setActif(true);
    User savedUser = userRepository.save(user);

    logService.log(savedUser, "Création d'un utilisateur");

    return savedUser;
}


    public User setActifStatus(Long userId, boolean actif) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        user.setActif(actif);
        User updatedUser = userRepository.save(user);
        logService.log(updatedUser, (actif ? "Activation" : "Désactivation") + " de l'utilisateur");
        return updatedUser;
    }
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur introuvable avec l'ID : " + id);
        }
        userRepository.deleteById(id);
    }


    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));
    
        existingUser.setNom(updatedUser.getNom());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setActif(updatedUser.isActif());
        // ajoute d’autres champs à mettre à jour selon ton modèle
        if (updatedUser.getMotDePasse() != null && !updatedUser.getMotDePasse().isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(updatedUser.getMotDePasse());
            existingUser.setMotDePasse(hashedPassword);
        }
        
          // Mise à jour du rôle si un ID de rôle est fourni
    if (updatedUser.getRole() != null) {
        Role role = roleRepository.findById(updatedUser.getRole().getId())
            .orElseThrow(() -> new RuntimeException("Rôle introuvable avec l'ID : " + updatedUser.getRole().getId()));
        existingUser.setRole(role);
    }
        return userRepository.save(existingUser);
    }

    
    


}
