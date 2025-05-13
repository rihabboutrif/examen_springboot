import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { User } from '../models/user.model';
import { Role } from '../models/role.model';
import { Permission } from '../models/permission.model';
import { UserService } from '../services/user.service';
import { RoleService } from '../services/role.service';
import { PermissionService } from '../services/permission.service';
import { NgFor, NgIf } from '@angular/common';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-users',
  standalone: true,
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgFor,
    NgIf,
    MatPaginatorModule
  ]
})
export class UsersComponent implements OnInit {
  users: User[] = [];
  rolesDisponibles: Role[] = [];
  permissionsDisponibles: Permission[] = [];
  userForm: FormGroup;
  editingUserId: number | null = null;

  currentPage: number = 0;
  pageSize: number = 5;
  totalUsers: number = 0;

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private permissionService: PermissionService,
    private authService: AuthService,
    private fb: FormBuilder
  ) {
    this.userForm = this.fb.group({
      username: [''],
      email: [''],
      roles: [[]],
      permissions: [[]]
    });
  }


  hasPermission(permission: string): boolean {
  return this.authService.hasPermission(permission);
}
  ngOnInit(): void {
    this.loadUsers();
    this.loadRoles();
    this.loadPermissions();
  }

  loadUsers(): void {
    this.userService.getAllPaginated(this.currentPage, this.pageSize).subscribe((response) => {
      this.users = response.content;
      this.totalUsers = response.totalElements;
    });
  }

  loadRoles(): void {
    this.roleService.getAllPaginated(0, 100).subscribe(res => {
      this.rolesDisponibles = res.content;
    });
  }

  loadPermissions(): void {
    this.permissionService.getAll(0, 100).subscribe(res => {
      this.permissionsDisponibles = res.content;
    });
  }

  submit(): void {
    const userData = this.userForm.value;
    if (this.editingUserId) {
      this.userService.update(this.editingUserId, userData).subscribe(() => {
        this.loadUsers();
        this.resetForm();
      });
    } else {
      this.userService.create(userData).subscribe(() => {
        this.loadUsers();
        this.resetForm();
      });
    }
  }

  editUser(user: User): void {
    this.editingUserId = user.id;
    this.userForm.patchValue({
      username: user.nom,
      email: user.email,
      roles: user.role,
      permissions: user.permissions
    });
  }

  deleteUser(id: number): void {
    if (confirm('Confirmer la suppression ?')) {
      this.userService.delete(id).subscribe(() => {
        this.loadUsers();
      });
    }
  }

  resetForm(): void {
    this.userForm.reset();
    this.userForm.patchValue({ roles: [], permissions: [] });
    this.editingUserId = null;
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadUsers();
  }

  isPermissionChecked(perm: Permission): boolean {
    return this.userForm.value.permissions?.some((p: Permission) => p.id === perm.id);
  }

  isRoleChecked(role: Role): boolean {
    return this.userForm.value.roles?.some((r: Role) => r.id === role.id);
  }

  onRoleToggle(role: Role, checked: boolean): void {
    const roles = this.userForm.value.roles || [];
    if (checked) {
      this.userForm.patchValue({ roles: [...roles, role] });
    } else {
      this.userForm.patchValue({ roles: roles.filter((r: Role) => r.id !== role.id)
});
    }
  }

  onPermissionToggle(perm: Permission, checked: boolean): void {
    const permissions = this.userForm.value.permissions || [];
    if (checked) {
      this.userForm.patchValue({ permissions: [...permissions, perm] });
    } else {
      this.userForm.patchValue({ permissions: permissions.filter((p: Permission) => p.id !== perm.id)
 });
    }
  }

  // 🟡 Méthodes pour gérer les checkbox proprement
  onRoleCheckboxChange(event: Event, role: Role): void {
    const input = event.target as HTMLInputElement;
    this.onRoleToggle(role, input.checked);
  }

  onPermissionCheckboxChange(event: Event, perm: Permission): void {
    const input = event.target as HTMLInputElement;
    this.onPermissionToggle(perm, input.checked);
  }
}
