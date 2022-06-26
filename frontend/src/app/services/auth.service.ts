import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly AUTH_URL = 'http://localhost:8080/basicauth';
  private readonly USERNAME_LOCAL_STORAGE = 'g4-username';
  private readonly FULLNAME_LOCAL_STORAGE = 'g4-fullname';
  private readonly PASSWORD_LOCAL_STORAGE = 'g4-password';

  constructor(private http: HttpClient) {}

  authenticate(username: string, password: string) {
    this.http.get(this.AUTH_URL, {
      headers: {
        authorization: this.createBasicAuthToken(username, password),
      },
    });
  }

  createBasicAuthToken(username: string, password: string): string {
    return 'Basic ' + window.btoa(username + ':' + password);
  }

  isAuthenticated(): boolean {
    const username = localStorage.getItem(this.USERNAME_LOCAL_STORAGE);
    return username !== null;
  }

  setUsername(username: string): void {
    localStorage.setItem(this.USERNAME_LOCAL_STORAGE, username);
  }

  setPassword(password: string): void {
    localStorage.setItem(this.PASSWORD_LOCAL_STORAGE, password);
  }

  setFullName(fullName: string): void {
    localStorage.setItem(this.FULLNAME_LOCAL_STORAGE, fullName);
  }

  getFullName() {
    return localStorage.getItem(this.FULLNAME_LOCAL_STORAGE);
  }

  logout() {
    localStorage.removeItem(this.USERNAME_LOCAL_STORAGE);
    localStorage.removeItem(this.PASSWORD_LOCAL_STORAGE);
    localStorage.removeItem(this.FULLNAME_LOCAL_STORAGE);
  }
}
