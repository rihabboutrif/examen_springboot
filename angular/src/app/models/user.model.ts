import { Permission } from "./permission.model";
import { Role } from "./role.model";

export interface User {
    id: number;
    username: string;
    email: string;
    roles: Role[];
    permissions: Permission[];
  }
  