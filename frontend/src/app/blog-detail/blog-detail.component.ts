import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthUser, Blog } from '../blog/blog';
import { BlogService } from '../blog/blog.service';
import { StoreUser } from '../login/storeUser.service';

@Component({
  selector: 'app-blog-detail',
  templateUrl: './blog-detail.component.html',
  styleUrls: ['./blog-detail.component.css'],
})
export class BlogDetailComponent implements OnInit {
  blog!: Blog;
  x: number = 0;
  roleChecker?: AuthUser;

  
  constructor(
    private blogService: BlogService,
    private route: ActivatedRoute,
    private router: Router,
    private storeuser: StoreUser
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.getBlogById(id);
    this.blogService.getAuthUsersByUsername(this.storeuser.getUsername()).subscribe((authuser) => (this.roleChecker = authuser));
  
  }

  getBlogById(id: number): void {
    this.blogService.getBlogById(id).subscribe((blog) => (this.blog = blog));
  }
  updateBlog(blog: Blog): void{
    this.blogService.updateEntries(blog);
    this.storeuser.setBlog(this.blog);
    this.router.navigate(['update']);
  }
  deleteBlog(blog: Blog): void{
    this.x = blog.id as number;
    this.blogService.deleteBlogById(this.x).subscribe(data => {
      console.log(data);
    this.router.navigate(['home']);

    });
  }
  validateUser(){
    return this.storeuser.getUsername() == this.blog.author || 
    this.roleChecker?.role == "Admin";
  }
}
