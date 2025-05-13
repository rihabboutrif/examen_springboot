import { Injectable, inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';
import { tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://localhost:8081/api/auth';
  private isBrowser: boolean;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
    const platformId = inject(PLATFORM_ID);
    this.isBrowser = isPlatformBrowser(platformId);
  }

 login(email: string, motDePasse: string) {
  return this.http.post(`${this.baseUrl}/login`, { email, motDePasse }, { withCredentials: true }).pipe(
    tap((response: any) => {
      // Assuming the backend returns something like { token: "abc" }
    
        // Store token in local storage or set some state
        localStorage.setItem('connected', response.token);
        localStorage.setItem('user', JSON.stringify(response.user));

        this.setAuthenticated();
      
    })
  );
}


  logout() {
    this.http.post(`${this.baseUrl}/logout`, {}, { withCredentials: true }).subscribe(() => {
      if (this.isBrowser) {
        localStorage.removeItem('connected');
        localStorage.removeItem('user');
      }
      this.router.navigate(['/login']);
    });
  }

  isAuthenticated(): boolean {
    return this.isBrowser && localStorage.getItem('connected') === 'true';
  }

  getLoggedUser(): any {
    if (!this.isBrowser) return null;
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  hasPermission(permission: string): boolean {
    const user = this.getLoggedUser();
    return user?.permissions?.some((p: any) => p.nom === permission);
  }

  setAuthenticated() {
    if (this.isBrowser) {
      localStorage.setItem('connected', 'true');
    }
  }
}
