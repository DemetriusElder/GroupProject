import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;

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
    }
    console.log(this.signupForm.value);
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
