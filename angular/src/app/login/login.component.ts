import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service'; // ajuste le chemin selon ton projet
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  imports: [FormsModule]
})

export class LoginComponent {
  email: string = '';
  motDePasse: string = '';
  erreur: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.authService.login(this.email, this.motDePasse).subscribe({
      next: (message) => {
        console.log(message);
        this.router.navigate(['/dashboard']); // redirige après login (ajuste la route si besoin)
      },
      error: (err) => {
        this.erreur = err.error || 'Erreur lors de la connexion';
      }
    });
  }
}
