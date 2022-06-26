import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BlogDto } from 'src/app/models/blog';
import { AuthService } from 'src/app/services/auth.service';
import { BlogService } from 'src/app/services/blog.service';

@Component({
  selector: 'app-add-page',
  templateUrl: './add-page.component.html',
  styleUrls: ['./add-page.component.css'],
})
export class AddPageComponent {
  postForm = new FormGroup(
    {
      title: new FormControl('', [
        Validators.required,
        Validators.minLength(15),
      ]),
      imageUrl: new FormControl('', [Validators.required]),
      content: new FormControl('', [
        Validators.required,
        Validators.minLength(200),
      ]),
    },
    { updateOn: 'submit' }
  );

  errorMessage: string = '';

  constructor(
    private authService: AuthService,
    private blogService: BlogService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (!this.postForm.valid) {
      this.postForm.markAllAsTouched();
    } else {
      const blogDto: BlogDto = {
        imageUrl: this.postForm.get('imageUrl')?.value,
        title: this.postForm.get('title')?.value,
        content: this.postForm.get('content')?.value,
        author: this.authService.getFullName()!,
        username: this.authService.getUsername()!,
      };
      this.addBlog(blogDto);
    }
  }

  get title() {
    return this.postForm.get('title');
  }
  get imageUrl() {
    return this.postForm.get('imageUrl');
  }
  get content() {
    return this.postForm.get('content');
  }

  addBlog(blogDto: BlogDto) {
    const authToken = this.authService.createBasicAuthToken(
      this.authService.getUsername()!,
      this.authService.getPassword()!
    );
    console.log(authToken);
    this.blogService.addBlog(blogDto, authToken).subscribe(
      null,
      () => (this.errorMessage = 'Something wrong'),
      () => this.router.navigateByUrl('/')
    );
  }
}
