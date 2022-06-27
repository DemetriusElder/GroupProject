import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Blog, BlogDto } from 'src/app/models/blog';
import { AuthService } from 'src/app/services/auth.service';
import { BlogService } from 'src/app/services/blog.service';

@Component({
  selector: 'edit-add-page',
  templateUrl: './edit-page.component.html',
  styleUrls: ['./edit-page.component.css'],
})
export class EditPageComponent implements OnInit {
  editForm = new FormGroup(
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

  isLoading: boolean = false;
  errorMessage: string = '';
  id!: number;
  blog?: Blog;

  constructor(
    private authService: AuthService,
    private blogService: BlogService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
      this.getBlogById(this.id);
    });
  }

  onSubmit(): void {
    this.isLoading = true;
    if (!this.editForm.valid) {
      this.editForm.markAllAsTouched();
    } else {
      const blogDto: BlogDto = {
        imageUrl: this.editForm.get('imageUrl')?.value,
        title: this.editForm.get('title')?.value,
        content: this.editForm.get('content')?.value,
        username: this.authService.getUsername()!,
      };
      this.updateBlog(blogDto);
    }
    this.isLoading = false;
  }

  get title() {
    return this.editForm.get('title');
  }
  get imageUrl() {
    return this.editForm.get('imageUrl');
  }
  get content() {
    return this.editForm.get('content');
  }

  getBlogById(id: number): void {
    this.blogService.getBlogById(id).subscribe(
      (blog) => (this.blog = blog),
      null,
      () => {
        this.editForm.setValue({
          title: this.blog?.title,
          imageUrl: this.blog?.imageUrl,
          content: this.blog?.content,
        });
      }
    );
  }

  updateBlog(blogDto: BlogDto) {
    const authToken = this.authService.createBasicAuthToken(
      this.authService.getUsername()!,
      this.authService.getPassword()!
    );
    this.blogService.updateBlog(blogDto, authToken, this.id).subscribe(
      null,
      () => (this.errorMessage = 'Something wrong'),
      () => this.router.navigateByUrl(`/blogs/${this.id}`)
    );
  }
}
