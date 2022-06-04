import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Blog } from './blog';

@Injectable({
  providedIn: 'root',
})
export class BlogService {
  private blogsUrl = 'http://localhost:8080/entries';

  constructor(private http: HttpClient) {}

  getBlogs(): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.blogsUrl);
  }

  postBlog(blog: Blog): Observable<void> {
    return this.http.post<void>(this.blogsUrl, blog);
  }

  getBlogById(id: string): Observable<Blog> {
    return this.http.get<Blog>(`${this.blogsUrl}/${id}`);
  }
}
