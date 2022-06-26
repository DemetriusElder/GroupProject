import { Role } from './role';

export interface User {
  id: string;
  username: string;
  password: string;
  fullName: string;
  role: Role;
}
