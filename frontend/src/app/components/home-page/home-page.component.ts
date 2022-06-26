import { Component, OnInit } from '@angular/core';
import { Blog } from 'src/app/models/blog';
import { Page } from 'src/app/models/page';
import { AuthService } from 'src/app/services/auth.service';
import { BlogService } from 'src/app/services/blog.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  blogs: Blog[] = [];
  page: number = 0;
  size: number = 9;
  last: boolean = false;
  isAuthenticated: boolean = false;

  constructor(
    private blogService: BlogService,
    private authService: AuthService
  ) {
    this.isAuthenticated = this.authService.isAuthenticated();
  }

  ngOnInit(): void {
    this.getBlogs();
  }

  onFetchMore() {
    this.getBlogs();
  }

  getBlogs() {
    this.blogService
      .getBlogs(this.page, this.size)
      .subscribe(({ content, last }) => {
        this.blogs = [...this.blogs, ...content];
        this.last = last;
      });
    this.page++;
  }
}
