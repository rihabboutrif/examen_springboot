package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repositories.*;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataSeeder {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoriqueActionRepository historiqueActionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedData() {
            System.out.println("Seeding data...");

     //   if (!roleRepository.findAll().isEmpty()) return;

        // --- Permissions ---
        Permission createUser = new Permission();
        createUser.setNom("CREATE USER");
        createUser.setDescription("Create a new user");

        Permission readUser = new Permission();
        readUser.setNom("READ USER");
        readUser.setDescription("Read user information");

        Permission updateUser = new Permission();
        updateUser.setNom("UPDATE USER");
        updateUser.setDescription("Update user information");

        Permission deleteUser = new Permission();
        deleteUser.setNom("DELETE USER");
        deleteUser.setDescription("Delete a user");

        Permission createRole = new Permission();
        createRole.setNom("CREATE ROLE");
        createRole.setDescription("Create a new role");

        Permission readRole = new Permission();
        readRole.setNom("READ ROLE");
        readRole.setDescription("Read role information");

        Permission updateRole = new Permission();
        updateRole.setNom("UPDATE ROLE");
        updateRole.setDescription("Update role information");

        Permission deleteRole = new Permission();
        deleteRole.setNom("DELETE ROLE");
        deleteRole.setDescription("Delete a role");

        Permission createPermission = new Permission();
        createPermission.setNom("CREATE PERMISSION");
        createPermission.setDescription("Create a new permission");

        Permission readPermission = new Permission();
        readPermission.setNom("READ PERMISSION");
        readPermission.setDescription("Read permission information");

        Permission updatePermission = new Permission();
        updatePermission.setNom("UPDATE PERMISSION");
        updatePermission.setDescription("Update permission information");

        Permission deletePermission = new Permission();
        deletePermission.setNom("DELETE PERMISSION");
        deletePermission.setDescription("Delete a permission");

        Permission readHistory = new Permission();
        readHistory.setNom("READ HISTORY");
        readHistory.setDescription("Read user own history");

        permissionRepository.saveAll(List.of(
            createUser, readUser, updateUser, deleteUser,
            createRole, readRole, updateRole, deleteRole,
            createPermission, readPermission, updatePermission, deletePermission,
            readHistory
        ));

        // --- Roles ---
        Role adminRole = new Role();
        adminRole.setNom("ADMIN");
        adminRole.setDescription("Administrator");
        adminRole.setPermissions(new HashSet<>(List.of(
            createUser, readUser, updateUser, deleteUser,
            createRole, readRole, updateRole, deleteRole,
            createPermission, readPermission, updatePermission, deletePermission,
            readHistory
        )));

        Role userRole = new Role();
        userRole.setNom("USER");
        userRole.setDescription("Regular user");
        userRole.setPermissions(new HashSet<>(List.of(
            readUser, readRole, readPermission, readHistory
        )));

        roleRepository.saveAll(List.of(adminRole, userRole));

        // --- Users ---
        User admin = new User();
        admin.setNom("Admin");
        admin.setEmail("admin@example.com");
        admin.setMotDePasse(passwordEncoder.encode("admin123"));
        admin.setActif(true);
        admin.setRole(adminRole);

        User user = new User();
        user.setNom("User");
        user.setEmail("user@example.com");
        user.setMotDePasse(passwordEncoder.encode("user123"));
        user.setActif(true);
        user.setRole(userRole);

        userRepository.saveAll(List.of(admin, user));

        // --- Historique Actions ---
        HistoriqueAction hist1 = new HistoriqueAction();
        hist1.setAction("Admin account created");
        hist1.setDate(LocalDateTime.now().minusDays(1));
        hist1.setUtilisateur(admin);

        HistoriqueAction hist2 = new HistoriqueAction();
        hist2.setAction("User account created");
        hist2.setDate(LocalDateTime.now().minusHours(12));
        hist2.setUtilisateur(user);

        historiqueActionRepository.saveAll(List.of(hist1, hist2));
    }
}
