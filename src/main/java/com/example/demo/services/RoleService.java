package com.example.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;

@Service
public class RoleService {
    @Autowired private RoleRepository roleRepository;
    @Autowired private LogService logService;

    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public Role createRole(Role role, User admin) {
        if (roleRepository.existsByNom(role.getNom())) {
            throw new RuntimeException("Le rôle existe déjà");
        }
        Role saved = roleRepository.save(role);
        logService.log(admin, "Création du rôle : " + role.getNom());
        return saved;
    }

    public Role updateRole(Long id, Role newRole, User admin) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rôle introuvable"));
        role.setNom(newRole.getNom());
        role.setDescription(newRole.getDescription());
        role.setPermissions(newRole.getPermissions());
        Role updated = roleRepository.save(role);
        logService.log(admin, "Mise à jour du rôle : " + updated.getNom());
        return updated;
    }

    public void deleteRole(Long id, User admin) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Rôle introuvable"));
        roleRepository.delete(role);
        logService.log(admin, "Suppression du rôle : " + role.getNom());
    }
}
