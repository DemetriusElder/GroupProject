import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from './blog';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  private blogsUrl = 'http://localhost:3000/posts';
  constructor(private http: HttpClient) {}

  getBlogs(): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.blogsUrl);
  }

  postBlog(blog: Blog): void {
    this.http.post<Blog[]>(this.blogsUrl, blog);
  }
}
