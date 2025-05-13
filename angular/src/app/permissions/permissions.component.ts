import { Component, OnInit } from '@angular/core';
import { PermissionService } from '../services/permission.service';
import { Permission } from '../models/permission.model';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-permissions',
  templateUrl: './permissions.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, MatPaginatorModule], // ✅ Ajoute FormsModule ici
})
export class PermissionsComponent implements OnInit {
  permissions: Permission[] = [];
  newPermission: Permission = { id: 0, nom: '', description: '' };
  isEditing = false;

  totalPermissions = 0;
  pageSize = 5;
  currentPage = 0;

  constructor(private permissionService: PermissionService,private authService: AuthService) {}

  ngOnInit(): void {
    this.loadPermissions();
  }


  hasPermission(permission: string): boolean {
  return this.authService.hasPermission(permission);
}
  loadPermissions(): void {
    this.permissionService.getAll(this.currentPage, this.pageSize).subscribe(page => {
      this.permissions = page.content;
      this.totalPermissions = page.totalElements;
    });
  }

  savePermission(): void {
    const action = this.isEditing
      ? this.permissionService.update(this.newPermission.id, this.newPermission)
      : this.permissionService.create(this.newPermission);

    action.subscribe(() => {
      this.loadPermissions();
      this.resetForm();
    });
  }

  editPermission(permission: Permission): void {
    this.newPermission = { ...permission };
    this.isEditing = true;
  }

  deletePermission(id: number): void {
    if (confirm('Supprimer cette permission ?')) {
      this.permissionService.delete(id).subscribe(() => this.loadPermissions());
    }
  }

  resetForm(): void {
    this.newPermission = { id: 0, nom: '', description: '' };
    this.isEditing = false;
  }

  onPageChange(event: PageEvent): void {
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadPermissions();
  }
}
