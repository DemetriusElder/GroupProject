import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';

  username!: String | string | null;
  password!: String | string | null;
  AUTH_TOKEN : string = 'authenticationToken';
  
  public isLoggedIn :EventEmitter<boolean> = new EventEmitter();

  constructor(private http: HttpClient) {
    this.isLoggedIn.emit(false);
    this.isUserLoggedIn();
  }

  authenticationService(username: String, password: String) {
    console.log("username:" + username);
    console.log("thisusername:" + this.username);
    console.log("password:" + password);
    console.log("thispassword:" + this.password);
    return this.http
      .get(`http://localhost:8080/api/v1/basicauth`, {
        headers: {
          authorization: this.createBasicAuthToken(username, password),
        },
      })
      .pipe(
        map((res) => {
          this.username = username;
          this.password = password;
          this.registerSuccessfulLogin(username as string, password);
        })
      );
  }

  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ':' + password);
  }

  createBasicAuthTokenNoHeader(username: String, password: String) {
    return window.btoa(username + ':' + password)
  }

  registerSuccessfulLogin(username: string, password: String) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    sessionStorage.setItem(this.AUTH_TOKEN, this.createBasicAuthTokenNoHeader(username as string, password));
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.AUTH_TOKEN);
    this.username = null;
    this.password = null;
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.AUTH_TOKEN);
    if (user === null){
    this.isLoggedIn.emit(false);
    return false;
    }else{
    this.isLoggedIn.emit(true);
    return true;
  }
    return true;
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    if (user === null) return '';
    return user;
  }
  getAuthToken(){
    return sessionStorage.getItem(this.AUTH_TOKEN);
  }
}
