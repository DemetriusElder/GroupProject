import { Component, OnInit } from '@angular/core';
import { Blog } from '../blog/blog';
import { BlogService } from '../blog/blog.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css'],
})
export class BlogListComponent implements OnInit {
  blogs: Blog[] = [];
  count: any = 0;
  max: number = 0;
  pagenumber: number = 1;
  private blogsUrl = 'http://localhost:8080/entries';

  constructor(private blogService: BlogService, private http: HttpClient) {}

  ngOnInit(): void {
    this.getBlogs();
  }

  getBlogs() {
    this.blogService
      .getPageBlogs(this.pagenumber)
      .subscribe((blogs) => (this.blogs = blogs.reverse()));
    this.blogService.getCount().subscribe((y: number) => {
      this.count = y;
      this.max = Math.ceil(this.count / 6);
    });
  }
  changePage(x: number) {
    this.pagenumber = x;
    console.log('Max:' + this.max);
    console.log('count:' + this.count);
    console.log('pagenumber:' + this.pagenumber);
    this.getBlogs();
  }
}
