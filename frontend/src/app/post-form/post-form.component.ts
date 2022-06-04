import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { BlogService } from '../blog/blog.service';

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

  constructor(
    private formBuilder: FormBuilder,
    private blogService: BlogService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      image: ['', Validators.required],
      content: ['', Validators.required],
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.form.invalid) {
      return;
    }
    const blog = {
      title: this.f['title'].value,
      author: this.f['author'].value,
      imageUrl: this.f['image'].value,
      content: this.f['content'].value,
    };
    console.log(blog);
    this.blogService.postBlog(blog);
  }
  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }
}