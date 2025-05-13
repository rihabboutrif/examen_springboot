import { User } from "./user.model";

export interface History {
    id: number;
    user: User;
    action: string;
    timestamp: Date;
  }
  