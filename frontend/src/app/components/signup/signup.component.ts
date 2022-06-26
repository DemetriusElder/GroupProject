import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.signupForm = new FormGroup(
      {
        firstName: new FormControl('', [Validators.required]),
        lastName: new FormControl('', [Validators.required]),
        username: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
        ]),
        password: new FormControl('', [
          Validators.required,
          Validators.minLength(6),
        ]),
      },
      { updateOn: 'submit' }
    );
  }

  onSubmit(): void {
    if (!this.signupForm.valid) {
      this.signupForm.markAllAsTouched();
    } else {
      this.signup();
    }
  }

  signup(): void {
    this.authService
      .signup(
        `${this.signupForm.get('firstName')!.value} ${
          this.signupForm.get('lastName')!.value
        }`,
        this.signupForm.get('username')!.value,
        this.signupForm.get('password')!.value
      )
      .subscribe(
        null,
        () =>
          (this.errorMessage =
            'We cannot create your account. Username is already taken.'),
        () => this.router.navigateByUrl('/login')
      );
  }

  get firstName() {
    return this.signupForm.get('firstName');
  }

  get lastName() {
    return this.signupForm.get('lastName');
  }

  get username() {
    return this.signupForm.get('username');
  }

  get password() {
    return this.signupForm.get('password');
  }
}
