import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  errorMessage: string = '';

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.login();
  }

  login() {
    this.authService
      .authenticate(
        this.loginForm.get('username')!.value,
        this.loginForm.get('password')!.value
      )
      .subscribe(
        (user: User) => {
          this.authService.setUser(user);
        },
        () =>
          (this.errorMessage =
            'We couldn’t find an account matching the username and password you entered. Please check your username and password and try again.'),
        () => this.router.navigateByUrl('/')
      );
  }
}
