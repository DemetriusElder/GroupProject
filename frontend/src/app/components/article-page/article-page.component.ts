import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Blog } from 'src/app/models/blog';
import { Role } from 'src/app/models/role';
import { AuthService } from 'src/app/services/auth.service';
import { BlogService } from 'src/app/services/blog.service';
import { faTrash, faPenToSquare } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-article-page',
  templateUrl: './article-page.component.html',
  styleUrls: ['./article-page.component.css'],
})
export class ArticlePageComponent implements OnInit {
  blog?: Blog;
  faTrash = faTrash;
  id!: number;
  faPenToSquare = faPenToSquare;
  constructor(
    private blogService: BlogService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getBlogById(this.id);
  }

  getBlogById(id: number): void {
    this.blogService.getBlogById(id).subscribe((blog) => (this.blog = blog));
  }

  canDelete() {
    return (
      this.authService.getRole() === Role.ADMIN ||
      this.authService.getFullName() === this.blog?.author
    );
  }

  canEdit() {
    return this.authService.getFullName() === this.blog?.author;
  }

  delete() {
    const authToken = this.authService.createBasicAuthToken(
      this.authService.getUsername()!,
      this.authService.getPassword()!
    );
    this.blogService
      .deleteBlog(this.authService.getUsername()!, authToken, this.id)
      .subscribe(
        null,
        () => console.log('error'),
        () => this.router.navigateByUrl('/')
      );
  }
}
