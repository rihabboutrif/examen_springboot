package com.example.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired private UserService userService;
        @Autowired private UserRepository userRepository;


    @GetMapping
    public Page<User> list(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}/actif")
    public User setActif(@PathVariable Long id, @RequestParam boolean actif) {
        return userService.setActifStatus(id, actif);
    }
    @PutMapping("/{id}")
public User update(@PathVariable Long id, @RequestBody User user) {
    return userService.updateUser(id, user);
}

@DeleteMapping("/{id}")
public void delete(@PathVariable Long id) {
    userService.deleteUser(id);
}


@GetMapping("/count")
public long countUsers() {
    return userRepository.countUsers();
}

}
