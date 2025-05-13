/*import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UsersComponent } from './users/users.component';
import { ReportsComponent } from './reports/reports.component';
import { LoginComponent } from './login/login.component';
import { RoleComponent } from './roles/roles.component';
import { PermissionsComponent } from './permissions/permissions.component';
import { HistoriqueComponent } from './historique/historique.component';
import { AuthGuard } from './guards/auth.guard';
import { PermissionGuard } from './guards/permission.guard';
import { SettingsComponent } from './Unauthorized/settings.component';

export const routes: Routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'users', component: UsersComponent , canActivate: [AuthGuard,PermissionGuard],
    data: {
      permissions: ['CREATE USER', 'UPDATE USER','DELETE USER','READ USER']  // Permissions nécessaires pour accéder à cette route
    }},
    { path: 'roles', component: RoleComponent , canActivate: [AuthGuard,PermissionGuard],
    data: {
      permissions: ['CREATE ROLE', 'UPDATE ROLE','DELETE ROLE','READ ROLE']  // Permissions nécessaires pour accéder à cette route
    }},
  { path: 'permissions', component: PermissionsComponent , canActivate: [AuthGuard,PermissionGuard],
    data: {
      permissions: ['CREATE PERMISSION', 'UPDATE PERMISSION','DELETE PERMISSION','READ PERMISSION']  // Permissions nécessaires pour accéder à cette route
    }},
  { path: 'historique', component: HistoriqueComponent, canActivate: [AuthGuard,PermissionGuard],
    data: {
      permissions: ['READ HISTORY']  // Permissions nécessaires pour accéder à cette route
    }},

  { path: 'reports', component: ReportsComponent, canActivate: [AuthGuard] },
  { path: 'unauthorized', component: SettingsComponent},
  { path: 'login', component: LoginComponent},
];
*/
import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UsersComponent } from './users/users.component';
import { ReportsComponent } from './reports/reports.component';
import { LoginComponent } from './login/login.component';
import { RoleComponent } from './roles/roles.component';
import { PermissionsComponent } from './permissions/permissions.component';
import { HistoriqueComponent } from './historique/historique.component';
import { AuthGuard } from './guards/auth.guard';
import { SettingsComponent } from './Unauthorized/settings.component';

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'users', component: UsersComponent, canActivate: [AuthGuard] },
  { path: 'roles', component: RoleComponent, canActivate: [AuthGuard] },
  { path: 'permissions', component: PermissionsComponent, canActivate: [AuthGuard] },
  { path: 'historique', component: HistoriqueComponent, canActivate: [AuthGuard] },
  { path: 'reports', component: ReportsComponent, canActivate: [AuthGuard] },
  { path: 'unauthorized', component: SettingsComponent },
  { path: 'login', component: LoginComponent },
];
