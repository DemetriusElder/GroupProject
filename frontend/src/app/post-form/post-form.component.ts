import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { BlogService } from '../blog/blog.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { StoreUser } from '../login/storeUser.service';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css'],
})
export class PostFormComponent implements OnInit {
  form: FormGroup = new FormGroup({
    title: new FormControl(''),
    author: new FormControl(''),
    image: new FormControl(''),
    content: new FormControl(''),
  });
  submitted = false;
  profileJson?: string;
  testUser: any;

  constructor(
    private formBuilder: FormBuilder,
    private blogService: BlogService,
    private router: Router,
    private authenticationService: AuthenticationService,
    private storeuser: StoreUser
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      title: ['', Validators.required],
      image: ['', Validators.required],
      content: ['', Validators.required],
    });
    this.testUser = this.storeuser.getUsername();
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.form.invalid) {
      alert('Please fill in required inputs.');
      return;
    }
    const blog = {
      title: this.f['title'].value,
      author: this.testUser.getUsername,
      imageUrl: this.f['image'].value,
      content: this.f['content'].value,
    };
    this.router.navigate(['home']);
    this.blogService.postBlog(blog).subscribe();
  }
  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  ifInvalid() {}
}
