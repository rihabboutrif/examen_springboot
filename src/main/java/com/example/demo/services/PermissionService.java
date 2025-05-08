package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.models.Permission;
import com.example.demo.models.User;
import com.example.demo.repositories.PermissionRepository;
@Service
public class PermissionService {
    @Autowired private PermissionRepository permissionRepository;
    @Autowired private LogService logService;

    public Page<Permission> getAllPermissions(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    public Permission createPermission(Permission permission, User admin) {
        if (permissionRepository.existsByNom(permission.getNom())) {
            throw new RuntimeException("Permission déjà existante");
        }
        Permission saved = permissionRepository.save(permission);
        logService.log(admin, "Ajout de la permission : " + permission.getNom());
        return saved;
    }

    public Permission updatePermission(Long id, Permission newPermission, User admin) {
        Permission p = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission introuvable"));
        p.setNom(newPermission.getNom());
        p.setDescription(newPermission.getDescription());
        Permission updated = permissionRepository.save(p);
        logService.log(admin, "Mise à jour de la permission : " + p.getNom());
        return updated;
    }

    public void deletePermission(Long id, User admin) {
        Permission p = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission introuvable"));
        permissionRepository.delete(p);
        logService.log(admin, "Suppression de la permission : " + p.getNom());
    }
}