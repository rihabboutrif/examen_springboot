import { Component, OnInit } from '@angular/core';
import { RoleService } from '../services/role.service';
import { Role } from '../models/role.model';
import { PageEvent } from '@angular/material/paginator';
import { MatPaginatorModule } from '@angular/material/paginator';  // Import du module MatPaginator
import { CommonModule } from '@angular/common';  // Import de CommonModule (nécessaire pour les directives comme *ngFor)
import { MatTableModule } from '@angular/material/table';  // Si tu utilises également MatTable
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-role',
  templateUrl: './roles.component.html',
   imports: [CommonModule, FormsModule, MatPaginatorModule], // ✅ Ajoute FormsModule ici

})
export class RoleComponent implements OnInit {
  roles: Role[] = [];
  newRole: Role = { id: 0, name: '', description: '', permissions: [] };
  isEditing: boolean = false;

  // Pagination
  pageSize: number = 5;
  currentPage: number = 0;
  totalRoles: number = 0;

  constructor(private roleService: RoleService) {}

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(): void {
    this.roleService.getAllPaginated(this.currentPage, this.pageSize).subscribe((response) => {
      this.roles = response.content;
      this.totalRoles = response.totalElements;
    });
  }

  saveRole(): void {
    if (this.isEditing) {
      this.roleService.update(this.newRole.id, this.newRole).subscribe(() => {
        this.resetForm();
        this.loadRoles();
      });
    } else {
      this.roleService.create(this.newRole).subscribe(() => {
        this.resetForm();
        this.loadRoles();
      });
    }
  }

  editRole(role: Role): void {
    this.newRole = { ...role, permissions: [...role.permissions] };
    this.isEditing = true;
  }

  deleteRole(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce rôle ?')) {
      this.roleService.delete(id).subscribe(() => this.loadRoles());
    }
  }

  resetForm(): void {
    this.newRole = { id: 0, name: '', description: '', permissions: [] };
    this.isEditing = false;
  }

  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadRoles();
  }
}
