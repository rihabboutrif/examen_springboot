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

import com.example.demo.models.Role;
import com.example.demo.services.MyUserDetails;
import com.example.demo.services.RoleService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired private RoleService roleService;
    @Autowired private RoleRepository roleRepository;

    @GetMapping
    public Page<Role> list(Pageable pageable) {
        return roleService.getAllRoles(pageable);
    }

   @PostMapping
public Role create(@RequestBody Role role, @AuthenticationPrincipal MyUserDetails userDetails) {
    User admin = userDetails.getUser();
    return roleService.createRole(role, admin);
}


    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role, @AuthenticationPrincipal User admin) {
        return roleService.updateRole(id, role, admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal User admin) {
        roleService.deleteRole(id, admin);
    }
    @GetMapping("/count")
public long countRoles() {
    return roleRepository.countRoles();
}

}