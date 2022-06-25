import { Component, OnInit } from '@angular/core';
import { Blog } from 'src/app/models/blog';
import { Pageable } from 'src/app/models/pageable';
import { BlogService } from 'src/app/services/blog.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  blogs: Blog[] = [];
  page: number = 0;
  size: number = 10;
  last: boolean = false;

  constructor(private blogService: BlogService) {}

  ngOnInit(): void {
    this.getBlogs();
  }

  onFetchMore() {
    this.getBlogs();
  }

  getBlogs() {
    this.blogService
      .getBlogs(this.page, this.size)
      .subscribe(({ content, last }: Pageable) => {
        this.blogs = [...this.blogs, ...content];
        this.last = last;
      });
    this.page++;
  }
}
