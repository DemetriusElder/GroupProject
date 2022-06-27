import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../models/role';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly AUTH_URL = 'http://localhost:8080/auth-users';
  private readonly USER_LOCAL_STORAGE = 'group4-user';

  constructor(private http: HttpClient) {}

  authenticate(username: string, password: string): Observable<User> {
    return this.http.post<User>(
      `${this.AUTH_URL}/login`,
      {
        username,
        password,
      },
      {
        headers: {
          authorization: this.createBasicAuthToken(username, password),
        },
      }
    );
  }

  createBasicAuthToken(username: string, password: string): string {
    return 'Basic ' + btoa(username + ':' + password);
  }

  isAuthenticated(): boolean {
    const user = localStorage.getItem(this.USER_LOCAL_STORAGE);
    return user !== null;
  }

  setUser(user: User): void {
    localStorage.setItem(this.USER_LOCAL_STORAGE, JSON.stringify(user));
  }

  getUser(): User | null {
    const stringifiedUser = localStorage.getItem(this.USER_LOCAL_STORAGE);
    if (stringifiedUser !== null) {
      return JSON.parse(stringifiedUser);
    }
    return null;
  }

  logout(): void {
    localStorage.removeItem(this.USER_LOCAL_STORAGE);
  }

  getUsername() {
    if (this.getUser() !== null) {
      return this.getUser()?.username;
    }
    return null;
  }

  getFullName() {
    if (this.getUser() !== null) {
      return this.getUser()?.fullName;
    }
    return null;
  }

  getPassword() {
    if (this.getUser() !== null) {
      return this.getUser()?.password;
    }
    return null;
  }

  getRole() {
    if (this.getUser() !== null) {
      return this.getUser()?.role;
    }
    return null;
  }

  isAdmin() {
    if (this.getUser() === null) {
      return false;
    }
    return this.getUser()?.role === Role.ADMIN;
  }

  signup(
    fullName: string,
    username: string,
    password: string
  ): Observable<void> {
    return this.http.post<void>(`${this.AUTH_URL}/signup`, {
      fullName,
      username,
      password,
    });
  }
}
