package com.example.demo.services;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.domain.Pageable;
@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private LogService logService;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User createUser(User user) {
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
}
