import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from './auth.service';
import { StoreUser } from './storeUser.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username!: string;
  password!: string;
  errorMessage = 'Invalid Credentials';
  successMessage!: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private storeuser: StoreUser
  ) {}

  ngOnInit() {}

  handleLogin() {
    this.authenticationService
      .authenticationService(this.username, this.password)
      .subscribe(
        (result) => {
          console.log(this.username);
          console.log(this.password);
          this.invalidLogin = false;
          this.loginSuccess = true;
          this.storeuser.setUsername(this.username);
          this.successMessage = 'Login Successful.';
          this.router.navigate(['home']);
        },
        () => {
          this.invalidLogin = true;
          this.loginSuccess = false;
        }
      );
  }
  redirectRegister(){
    this.router.navigate(['register']);
  }
}
