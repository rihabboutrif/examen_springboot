package com.example.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Permission;
import com.example.demo.services.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.models.User;


@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    @Autowired private PermissionService permissionService;

    @GetMapping
    public Page<Permission> list(Pageable pageable) {
        return permissionService.getAllPermissions(pageable);
    }

    @PostMapping
    public Permission create(@RequestBody Permission permission, @AuthenticationPrincipal User admin) {
        return permissionService.createPermission(permission, admin);
    }

    @PutMapping("/{id}")
    public Permission update(@PathVariable Long id, @RequestBody Permission permission, @AuthenticationPrincipal User admin) {
        return permissionService.updatePermission(id, permission, admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal User admin) {
        permissionService.deletePermission(id, admin);
    }
}
