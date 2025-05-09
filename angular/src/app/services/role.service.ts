import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from '../models/role.model';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  private apiUrl = 'http://localhost:8081/roles';

  constructor(private http: HttpClient) {}

  // Récupérer la liste des rôles paginée
  getAllPaginated(page: number, size: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }

  // Créer un nouveau rôle
  create(role: Role): Observable<Role> {
    return this.http.post<Role>(this.apiUrl, role);
  }

  // Mettre à jour un rôle existant
  update(id: number, role: Role): Observable<Role> {
    return this.http.put<Role>(`${this.apiUrl}/${id}`, role);
  }

  // Supprimer un rôle
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
