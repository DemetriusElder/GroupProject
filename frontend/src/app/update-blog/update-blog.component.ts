import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
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
import { Blog } from '../blog/blog';

@Component({
  selector: 'app-update-blog',
  templateUrl: './update-blog.component.html',
  styleUrls: ['./update-blog.component.css']
})
export class UpdateBlogComponent implements OnInit {
  form: FormGroup = new FormGroup({
    title: new FormControl(''),
    author: new FormControl(''),
    image: new FormControl(''),
    content: new FormControl(''),
  });
  submitted = false;
  profileJson?: string;
  testUser: any;
  tempblog?: Blog;
  blog: Blog = {};

  constructor(
    private formBuilder: FormBuilder,
    private blogService: BlogService,
    private router: Router,
    private authenticationService: AuthenticationService,
    private storeuser: StoreUser
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      title: [''],
      image: [''],
      content: [''],
    });
    this.tempblog = this.storeuser.getBlog();
  }
  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }
  onSubmit(): void {
    this.submitted = true;
    this.router.navigate(['home']);
    this.updateBlog();

  }
  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
  updateBlog(): void{
    this.blog = this.tempblog!;
    if (this.f['title'].value == null || this.f['title'].value == "")
      this.blog.title = this.tempblog?.title;
      else
      this.blog.title = this.f['title'].value;
    if (this.f['content'].value == null || this.f['content'].value == "")
      this.blog.content = this.tempblog?.content;
      else
      this.blog.content = this.f['content'].value;
    if (this.f['image'].value == null || this.f['image'].value == "")
      this.blog.imageUrl = this.tempblog?.imageUrl;
      else
      this.blog.imageUrl = this.f['image'].value;
    this.blogService.updateEntries(this.blog).subscribe();
  }

}
