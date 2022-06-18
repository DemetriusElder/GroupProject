import { Component, OnInit } from '@angular/core';
import { Blog } from '../blog/blog';
import { BlogService } from '../blog/blog.service';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css'],
})
export class BlogListComponent implements OnInit {
  blogs: Blog[] = [];
  p: number = 1;

  constructor(private blogService: BlogService) {}

  ngOnInit(): void {
    this.getBlogs();
  }

  getBlogs() {
    this.blogService
      .getBlogs()
      .subscribe((blogs) => (this.blogs = blogs.reverse()));
  }
}
