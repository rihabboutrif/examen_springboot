import { Permission } from "./permission.model";
import { Role } from "./role.model";

export interface User {
    id: number;
    nom: string;
    email: string;
    role: Role;
    permissions: Permission[];
    actif:boolean
  }
  