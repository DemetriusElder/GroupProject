import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Blog } from '../blog/blog';
import { BlogService } from '../blog/blog.service';

@Component({
  selector: 'app-blog-detail',
  templateUrl: './blog-detail.component.html',
  styleUrls: ['./blog-detail.component.css'],
})
export class BlogDetailComponent implements OnInit {
  blog!: Blog;
  tempblog: any;

  
  constructor(
    private blogService: BlogService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.getBlogById(id);
    this.tempblog = this.getBlogById(35);
  
  }

  getBlogById(id: number): void {
    this.blogService.getBlogById(id).subscribe((blog) => (this.blog = blog));
  }
  updateBlog(blog: Blog): void{
    this.blogService.updateEntries(blog);
  }
  deleteBlog(id: number): void{
    this.blogService.deleteBlogById(id).subscribe(data => {
      console.log(data);
    });
    console.log("TEST");
  }
}
