package com.example.demo.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserRepository userRepository;
@PostConstruct
public void init() {
    System.out.println("UserDetailsServiceImpl initialisé ✅");
}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    System.out.println("Email trouvé : " + user.getEmail());
System.out.println("Mot de passe (hashé) : " + user.getMotDePasse());

        Set<GrantedAuthority> authorities = new HashSet<>();
    
        if (user.getRole() != null && user.getRole().getPermissions() != null) {
            authorities = user.getRole().getPermissions().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getNom()))
                    .collect(Collectors.toSet());
        }
    
        return new MyUserDetails(user, authorities);

    }
    
}