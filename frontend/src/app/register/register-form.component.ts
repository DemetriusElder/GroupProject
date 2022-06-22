import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthUser } from '../blog/blog';
import { BlogService } from '../blog/blog.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
})
export class RegisterFormComponent implements OnInit {
  username!: string;
  password!: string;
  role!: string;
  errorMessage = 'Invalid Credentials';
  successMessage!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private blogService: BlogService
  ) {}

  ngOnInit() {
  }
  onChange(e: any) {
    if (e.target.checked) {
      this.role = "Admin";
    } else {
      this.role = "Normie";
    }
  }

  handleRegister() {
    const authuser = {
      username: this.username,
      password: this.password,
      role: this.role,
    };
    console.log(authuser);
    this.blogService.postAuthUsers(authuser).subscribe();
    this.router.navigate(['login']);
  }
}
